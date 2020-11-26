package com.andrii;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Writer {

    private final String path;

    public Writer(String path) {
        this.path = path;
    }

    public void write(byte[] data) {
        writeToFile(Arrays.toString(data));
    }

    public void write(String data) {
        writeToFile(data);
    }

    private void writeToFile(String data) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
