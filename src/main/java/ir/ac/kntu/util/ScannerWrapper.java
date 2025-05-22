package ir.ac.kntu.util;

import java.util.Scanner;

public class ScannerWrapper {
    private static final Scanner INSTANCE = new Scanner(System.in);

    private ScannerWrapper() {
    }


    public static String nextLine() {
        return INSTANCE.nextLine();
    }

    public static String next() {
        return INSTANCE.next();
    }

    public static int nextInt() {
        while (true) {
            try {
                return Integer.parseInt(INSTANCE.nextLine().trim());
            } catch (NumberFormatException e) {
                PrintHelper.printError("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public static double nextDouble() {
        while (true) {
            try {
                return Double.parseDouble(INSTANCE.nextLine().trim());
            } catch (NumberFormatException e) {
                PrintHelper.printError("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public static void close() {
        INSTANCE.close();
    }
}
