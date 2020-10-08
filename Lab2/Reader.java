package com.andrii.Lab2;

import com.andrii.color.ConsoleColors;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class Reader {

    public byte[] readBytes(String path) {
        File file = new File(path);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED_BACKGROUND + "           An error occurred:            " + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "Error reading file. File might not exist" + ConsoleColors.RESET);
        }
        return null;
    }

    public List<String> readLines(String path) {
        File file = new File(path);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println(ConsoleColors.RED_BACKGROUND + "           An error occurred:            " + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "Error reading file. File might not exist" + ConsoleColors.RESET);
        }
        return null;
    }
}
