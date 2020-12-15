package com.andrii.Lab1;

import com.andrii.color.ConsoleColors;

import java.util.Arrays;

public class Writer {

    private String path;

    public Writer(String path) {
        this.path = path;
    }

    public void write(int[] data) {
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
            System.out.println(ConsoleColors.CYAN_BOLD + "\nSuccessfully wrote to the file." + ConsoleColors.RESET);
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED_BACKGROUND + "           An error occurred:            " + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "Error writing to file. File might not exist" + ConsoleColors.RESET);
        }

    }
}
