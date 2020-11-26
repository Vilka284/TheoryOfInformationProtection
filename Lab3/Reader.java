package com.andrii;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Reader {

    private final String path;

    public Reader(String path) {
        this.path = path;
    }

    public byte[] readBytes() {
        File file = new File(path);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readLines() {
        File file = new File(path);
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
