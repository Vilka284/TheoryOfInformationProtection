package com.andrii.Lab2;

import com.andrii.Lab1.Writer;
import com.andrii.color.ConsoleColors;

import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MD5 md = new MD5();
        Reader reader = new Reader();
        Writer writer = new Writer("result.txt");
        String result = null;

        menuDigest();
        int input = in.nextInt();
        in.nextLine();

        if (input == 1) {
            enter("Enter message:");

            String message = in.nextLine();
            result = md.getDigest(message.getBytes());
        } else if (input == 2) {
            enter("Enter file path:");

            String path = in.nextLine();
            byte[] fileBytes = reader.readBytes(path);
            result = md.getDigest(fileBytes);
        } else if (input == 3) {

            enter("Enter file path:");
            String path = in.nextLine();
            byte[] fileBytes = reader.readBytes(path);
            result = md.getDigest(fileBytes);

            enter("Enter file with MD5 hash path:");
            path = in.nextLine();

            System.out.println(result.equals(reader.readLines(path).get(0))
                    ? ConsoleColors.GREEN_BOLD + "TRUE" + ConsoleColors.RESET
                    : ConsoleColors.RED_BOLD + "FALSE" + ConsoleColors.RESET);
            exit("\n", 0);
        } else exit("Unknown action", -1);

        menuWrite();
        input = in.nextInt();

        if (input == 1) writer.write("Message digest: " + result);
        else if (input == 2) System.out.println("Message digest: " + result);
        else exit("Unknown action", -1);
    }

    private static void exit(String message, int status) {
        System.out.println(message);
        System.exit(status);
    }

    private static void enter(String message) {
        System.out.println(ConsoleColors.GREEN_BOLD
                + message
                + ConsoleColors.RESET);
    }

    private static void menuDigest() {
        System.out.println("+------------------------------------------+");
        System.out.println("|" + ConsoleColors.GREEN_BOLD
                + "           Select next options            "
                + ConsoleColors.RESET + "|");
        System.out.println("| 1. Generate message digest from input    |");
        System.out.println("| 2. Generate message digest from file     |");
        System.out.println("| 3. Check file integrity                  |");
        System.out.println("| (type number)                            |");
        System.out.println("+------------------------------------------+");
    }

    private static void menuWrite() {
        System.out.println("+------------------------------------------+");
        System.out.println("|" + ConsoleColors.GREEN_BOLD
                + "           Select next options            "
                + ConsoleColors.RESET + "|");
        System.out.println("| 1. Write to file                         |");
        System.out.println("| 2. Print here                            |");
        System.out.println("| (type number)                            |");
        System.out.println("+------------------------------------------+");
    }
}
