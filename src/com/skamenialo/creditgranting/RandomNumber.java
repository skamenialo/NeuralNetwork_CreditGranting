package com.skamenialo.creditgranting;

import java.util.Random;

public class RandomNumber {

    static double nextDouble(double min, double max) {
        Random rand = new Random();
        return min + rand.nextDouble() * (max - min);
    }

    static int nextInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    static double nextMoney(double min, double max) {
        double p = nextDouble(nextDouble(min, max / 2), nextDouble(max / 2, max));
        p = nextDouble(p - Math.abs((nextDouble(0, p / 2))), p + Math.abs((nextDouble(0, p / 2))));
        return Math.abs(p);
    }
}
