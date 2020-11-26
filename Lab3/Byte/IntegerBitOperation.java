package com.andrii.Byte;

public class IntegerBitOperation {

    private final int biteSizeNumber = Integer.SIZE;

    public int rotateLeft(int number, int n) {
        return (((number) << (n))) | ((number) >>> (biteSizeNumber - (n)));
    }

    public int rotateRight(int number, int n) {
        return (((number) >>> (n))) | ((number) << (biteSizeNumber - (n)));
    }

    public byte[] complementToBlock(int sizeOfBlock, byte[] data) {
        if (data == null) {
            System.out.println("Data is null");
        }
        int n = data.length;
        if (sizeOfBlock < n) {
            System.out.println("Size of block is smaller than data size");
        }
        byte[] tmp = new byte[sizeOfBlock];
        sizeOfBlock--;
        n--;
        for (int i = n; i >= 0; i--) {
            tmp[sizeOfBlock] = data[i];
            sizeOfBlock--;
        }
        return tmp;
    }

    public byte[] xorTwoBlocks(byte[] block1, byte[] block2) {
        if (block1.length != block2.length) {
            throw new RuntimeException("Block length must be equal");
        }
        byte[] tmp = new byte[block1.length];
        for (int i = 0; i < block1.length; i++) {
            tmp[i] = (byte) (block1[i] ^ block2[i]);
        }
        return tmp;
    }
}
