package com.skamenialo.creditgranting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static NeuralNetwork sNeuralNetwork;

    private static List<Client> getStaticLearningSet() {
        List<Client> learningElements = new ArrayList<>();
        learningElements.add(new Client(false, 18, 0, 0, 0, false, 0, 1000));
        learningElements.add(new Client(false, 40, 2, 1000, 12, true, 2000, 3000));
        learningElements.add(new Client(false, 46, 3, 2059.0, 11, true, 1223.0, 5218.0));
        learningElements.add(new Client(true, 55, 3, 4469.0, 36, false, 0.0, 3816.0));
        learningElements.add(new Client(false, 33, 2, 2515.0, 15, true, 2700.0, 3009.0));
        learningElements.add(new Client(false, 74, 0, 4005.0, 192, true, 4107.0, 3681.0));
        learningElements.add(new Client(false, 36, 3, 1743.0, 22, true, 4970.0, 8664.0));
        learningElements.add(new Client(true, 21, 2, 2141.0, 22, true, 278.0, 4092.0));
        learningElements.add(new Client(true, 69, 1, 3404.0, 252, false, 0.0, 3370.0));
        learningElements.add(new Client(false, 77, 1, 2122.0, 156, true, 3870.0, 4905.0));
        learningElements.add(new Client(false, 32, 2, 1674.0, 5, false, 0.0, 4315.0));
        learningElements.add(new Client(false, 33, 3, 3223.0, 6, true, 2033.0, 7736.0));
        learningElements.add(new Client(false, 52, 3, 2644.0, 15, false, 0.0, 8325.0));
        learningElements.add(new Client(true, 71, 0, 5158.0, 228, true, 3510.0, 4096.0));
        learningElements.add(new Client(false, 32, 2, 2401.0, 17, true, 3943.0, 5686.0));
        learningElements.add(new Client(true, 53, 3, 3297.0, 15, true, 630.0, 4240.0));
        learningElements.add(new Client(false, 60, 2, 2420.0, 33, true, 3086.0, 6145.0));
        learningElements.add(new Client(false, 79, 0, 2146.0, 132, true, 3463.0, 2870.0));
        learningElements.add(new Client(false, 68, 0, 1360.0, 264, false, 0.0, 5313.0));
        learningElements.add(new Client(true, 29, 0, 2076.0, 27, true, 2157.0, 3444.0));
        learningElements.add(new Client(true, 62, 0, 2995.0, 3, false, 0.0, 6929.0));
        learningElements.add(new Client(true, 52, 2, 4958.0, 22, true, 4541.0, 5350.0));
        learningElements.add(new Client(false, 26, 3, 1065.0, 17, false, 0.0, 4648.0));
        learningElements.add(new Client(false, 20, 3, 3653.0, 35, true, 4192.0, 7437.0));
        learningElements.add(new Client(false, 80, 2, 2336.0, 120, false, 0.0, 5641.0));
        learningElements.add(new Client(true, 24, 1, 1249.0, 19, false, 0.0, 1588.0));
        return learningElements;
    }

    public static List<Client> getStaticVerifyingSet() {
        List<Client> veryfyingElements = new ArrayList<>();
        veryfyingElements.add(new Client(27, 1, 2500, 36, false, 0, 5000));//przynano
        veryfyingElements.add(new Client(68, 0, 1000, (90 - 68) * 12, false, 0, 2000));//przyznano
        veryfyingElements.add(new Client(18, 0, 0, 0, false, 0, 1000));//nie przyznano
        veryfyingElements.add(new Client(40, 2, 1000, 12, true, 2000, 3000));//nie przyznano
        veryfyingElements.add(new Client(34, 3, 1500, 4, true, 1000, 4000));//nie przyznano
        veryfyingElements.add(new Client(23, 0, 1200, 16, false, 0, 5000));//nie wiem
        veryfyingElements.add(new Client(21, 2, 2141.0, 22, true, 278.0, 4092.0));//przyznano
        return veryfyingElements;
    }

    public static List<Client> getRandomSet(int min, int max) {

        List<Client> set = new ArrayList<>();
        int ilosc = Math.abs(RandomNumber.nextInt(min, max));
        for (int i = 0; i < ilosc; i++) {
            try {
                set.add(Client.getRandomClient());
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return set;
    }

    public static void main(String[] args) throws IOException {
        List<Client> learningSet = getStaticLearningSet();

        for (int i = 0; i < learningSet.size(); i++) {
            System.out.println("k[" + i + "]\t" + learningSet.get(i).toString());
        }
        int ilosc = 0;//Math.abs(RandomNumber.nextInt(10, 25));
        for (int i = 0; i < ilosc; i++) {
            learningSet.add(Client.getRandomClient());
        }

        sNeuralNetwork = new NeuralNetwork(learningSet);

        sNeuralNetwork.verifyElements(getStaticVerifyingSet());
    }
}
