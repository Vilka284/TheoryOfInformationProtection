package com.andrii.Lab1;

import com.andrii.color.ConsoleColors;

import java.util.Arrays;
import java.util.Scanner;

public class Lab1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        Writer writer = new Writer("result.txt");

        menuSequence();
        int input = in.nextInt();

        enter("Enter sequence length:");
        int length = in.nextInt();
        int[] sequence = new int[length];

        if (input == 1) sequence = random.generate(length);
        else if (input == 2) sequence = random.generateNonReproducible(length);
        else exit("Unknown action", -1);

        menuWrite();
        input = in.nextInt();

        if (input == 1) writer.write(sequence);
        else if (input == 2) System.out.println("Sequence: " + Arrays.toString(sequence));
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

    private static void menuSequence() {
        System.out.println("+------------------------------------------+");
        System.out.println("|" + ConsoleColors.GREEN_BOLD
                + "           Select next options            "
                + ConsoleColors.RESET + "|");
        System.out.println("| 1. Generate sequence                     |");
        System.out.println("| 2. Generate non reproducible sequence    |");
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
