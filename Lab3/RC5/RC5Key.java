package com.andrii.RC5;

import com.andrii.Byte.IntegerBitOperation;

public class RC5Key {
    private final int P = 0xB7E15163;
    private final int Q = 0x9E3779B9;
    private final int sizeOfWordInBite = 32;
    private final int sizeOfWordInByte = sizeOfWordInBite / 8;
    private final int sizeKeyInByte;
    private int numberOfWords;
    private final int numberOfRounds;
    private int c;
    private final byte[] key;
    private int[] words;
    private final IntegerBitOperation biteOperation;

    public RC5Key(byte[] key, int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        this.sizeKeyInByte = key.length;
        this.key = key;
        biteOperation = new IntegerBitOperation();
        init(this.numberOfRounds);
    }

    private void init(int numberOfRounds) {
        c = sizeKeyInByte / sizeOfWordInByte;
        if (c == 0) {
            c = 1;
        }
        numberOfWords = 2 * (numberOfRounds + 1);
        if (!(numberOfWords > (sizeKeyInByte - 1) / sizeOfWordInByte)) {
            throw new RuntimeException("The key is too long for the number of rounds");
        }
        generateWords();
    }

    private void generateWords() {
        int[] L = new int[numberOfWords];
        int n;
        int number;
        int a = 0;
        int b = 0;
        int x = 0;
        int y = 0;
        words = new int[numberOfWords];

        for (int i = sizeKeyInByte - 1; i >= 0; i--) {
            L[i / sizeOfWordInByte] = (L[i / sizeOfWordInByte] << 8) + key[i];
        }
        words[0] = P;
        for (int i = 1; i < numberOfWords; i++) {
            words[i] = words[i - 1] + Q;
        }
        if (sizeKeyInByte > c) {
            n = sizeKeyInByte;
        } else {
            n = c;
        }
        for (int i = 0; i < 3 * n; i++) {
            words[x] = biteOperation.rotateLeft(words[x] + a + b, 3);
            a = words[x];
            number = (a + b) % 32;
            L[y] = biteOperation.rotateLeft(L[y] + a + b, number);
            b = L[y];
            x = (x + 1) % numberOfWords;
            y = (y + 1) % c;
        }
    }

    public byte[] getKey() {
        return key;
    }

    public int[] getWords() {
        return words;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }
}
