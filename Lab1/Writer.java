package com.andrii.Lab1;

import com.andrii.color.ConsoleColors;

import java.io.*;
import java.util.Arrays;

public class Writer {

    private String file;

    public Writer(String file) {
        this.file = file;
    }

    public void write(int[] data) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(Arrays.toString(data));
            writer.close();
            System.out.println(ConsoleColors.CYAN_BOLD + "\nSuccessfully wrote to the file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
