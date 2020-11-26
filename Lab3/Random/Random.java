package com.andrii.Random;

import java.util.Arrays;

public class Random {

    private static final long MODULE = (long) (Math.pow(2, 29) - 1);
    private static final long MULTIPLIER = ((long) Math.pow(16, 3));
    private static final long INCREMENT = 6765;
    private static final long START = 23;

    public int[] generate(final int sequenceLength) {
        int[] result = new int[sequenceLength];
        //result[0] = (int) START;
        result[0] = (int) System.currentTimeMillis();
        for (int i = 1; i < sequenceLength; i++) {
            result[i] = (int) (((MULTIPLIER * result[i - 1]) + INCREMENT) % MODULE);
        }
        return result;
    }

    public int[] generateNonReproducible(final int sequenceLength) {
        int[] result = new int[sequenceLength];
        result[0] = (int) START;
        for (int i = 1; i < sequenceLength; i++) {
            result[i] = (int) ((MULTIPLIER * result[i - 1] + INCREMENT) % MODULE + (System.currentTimeMillis() % MODULE));
        }
        return result;
    }

    public int getRandomNumber() {
        int[] arr = generateNonReproducible(1000);
        return Arrays.hashCode(arr);
    }
}
