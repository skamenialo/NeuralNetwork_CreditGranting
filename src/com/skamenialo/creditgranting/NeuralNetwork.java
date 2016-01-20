package com.skamenialo.creditgranting;

import java.util.Collections;
import java.util.List;

public class NeuralNetwork {

    private int mAge,
            mFail,
            mMaxFail;
    private List<Client> mClients;
    private Neuron[] mNeurons;

    public NeuralNetwork(List<Client> clients) {
        mClients = clients;

        System.out.println("\nIlość elementów uczących: " + (clients.size()));

        System.out.println("\nWejścia:");
        setX();

        System.out.println("\nWartość oczekiwana:");
        setD();

        mNeurons = new Neuron[8];

        System.out.println("\nWagi neuronów przed nauką: ");
        for (int i = 0; i < mNeurons.length; i++) {
            mNeurons[i] = new Neuron();
            System.out.println("n[" + i + "]=(" + mNeurons[i] + ")");
        }
        mMaxFail = 10;
        learn();

        System.out.println("\n\nWagi neuronów po nauce: ");
        for (int i = 0; i < mNeurons.length; i++) {
            System.out.println("n[" + i + "]=(" + mNeurons[i] + ")");
        }
    }

    private void learn() {
        mAge = 0;
        System.out.println("\n\nDopuszczalna ilość błędów: " + mMaxFail);
        do {
            mFail = 0;
            for (Client c : mClients) {
                for (int j = 0; j < mNeurons.length; j++) {
                    if (c.d[j] != mNeurons[j].answer(c.x, false)) {
                        mNeurons[j].calculate(c.d[j], c.x);
                        mFail++;
                    }
                }
            }
            mAge++;
            Collections.shuffle(mClients);
        } while (mFail > mMaxFail);

        System.out.println("Ilość epok po skończonej nauce: " + mAge
                + "\nIlość błędów po skończonej nauce: " + mFail);
    }

    private double verify(double[] x) {
        double answer = 0;
        for (int i = 0; i < mNeurons.length; i++) {
            double odp = mNeurons[i].answer(x, true);
            answer += odp;
            System.out.println("Odpowiedź neuronu nr " + i + ": " + odp);
        }
        return answer;
    }

    private void setX() {
        for (int i = 0; i < mClients.size(); i++) {
            System.out.print("x[" + i + "]\t");
            mClients.get(i).setX(mClients);
            System.out.println();
        }
    }

    private void setD() {
        int size = mClients.size(),
                i = 0;
        for (Client c : mClients) {
            c.d = new int[size];
            int KD[] = new int[size],
                    kD1[] = c.getD();
            for (int j = 0; j < size; j++) {
                int equal = 0,
                        notequal = 0,
                        kiD[] = mClients.get(j).getD();
                for (int k = 0; k < kD1.length; k++) {
                    if (kD1[k] == kiD[k]) {
                        equal++;
                    } else {
                        notequal++;
                    }
                }
                if (equal >= notequal) {
                    KD[j] = 1;
                } else {
                    KD[j] = -1;
                }
            }

            kD1 = new int[size];
            int kD2[] = c.getD();
            for (int j = 0; j < size; j++) {
                int zero = 0,
                        one = 0,
                        kiD[] = mClients.get(j).getD();
                for (int k = 0; k < kD2.length; k++) {
                    if (kD2[k] == kiD[k]) {
                        if (kD2[k] == 0) {
                            zero++;
                        } else {
                            one++;
                        }
                    }
                }
                if (one >= zero) {
                    kD1[j] = 1;
                } else {
                    kD1[j] = -1;
                }
            }
            System.out.print("d[" + i + "]\t");
            for (int j = 0; j < KD.length; j++) {
                c.d[j] = KD[j] * kD1[j];
                if (c.d[j] == 1) {
                    System.out.print(" ");
                }
                System.out.print(c.d[j] + "|");
            }
            System.out.println();
            i++;
        }
    }

    public void verifyElements(List<Client> clients) {
        System.out.println("\nWejścia:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.print("x[" + i + "] ");
            clients.get(i).setX(clients);
            System.out.println();
        }
        System.out.println();
        for (Client c : clients) {
            c.setGranted((verify(c.x) > 0));
            System.out.println(c.isGrantedToString() + "\n");
        }
    }

    public void setFail(int fail) {
        mMaxFail = fail;
    }
}
