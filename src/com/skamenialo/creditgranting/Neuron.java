package com.skamenialo.creditgranting;

public class Neuron {

    public double w0, w[];//waga

    public Neuron() {
        w = new double[8];
        for (int i = 0; i < w.length; i++) {
            w[i] = RandomNumber.nextDouble(-1.0, 1.0);
        }
        w0 = 1;
    }

    public double answer(double[] x, boolean check) {
        double s = 0;
        if (!check) {
            s += w[0] * x[0];
        }
        for (int i = 1; i < x.length; i++) {
            s += w[i] * x[i];
        }
        s += w0;

        if (s >= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public void calculate(int d, double[] x) {
        for (int i = 0; i < x.length; i++) {
            w[i] += d * x[i];
        }
        w0 += d;
    }

    @Override
    public String toString() {
        String str = "w:" + w0;
        for (int i = 0; i < w.length; i++) {
            str += "\tw[i]:" + w[i] + "    ";
        }
        return str;
    }
}
