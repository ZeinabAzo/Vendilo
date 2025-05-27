package ir.ac.kntu.util;

import ir.ac.kntu.models.Address;
import ir.ac.kntu.models.Cart;
import ir.ac.kntu.models.Order;

import java.util.*;

import static ir.ac.kntu.util.ProductPrinter.centerText;

public class PrintHelper {

    public static class ConsoleColors {

        // Dim/Faint style
        public static final String DIM = "\u001B[2m";

        // Reset
        public static final String RESET = "\u001B[0m";

        // Regular Colors
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";

        // Bold Colors
        public static final String BRIGHT_RED = "\u001B[91m";
        public static final String BRIGHT_GREEN = "\u001B[92m";
        public static final String BRIGHT_YELLOW = "\u001B[93m";
        public static final String BRIGHT_BLUE = "\u001B[94m";
        public static final String BRIGHT_PURPLE = "\u001B[95m";
        public static final String BRIGHT_CYAN = "\u001B[96m";

        // Backgrounds
        public static final String BG_LIGHT_GRAY = "\u001B[48;5;235m"; // Light gray background
        public static final String BG_DARK_GRAY = "\u001B[48;5;234m"; // Darker gray background
        public static final String BG_RED = "\u001B[41m";
        public static final String BG_GREEN = "\u001B[42m";
        public static final String BG_BLUE = "\u001B[44m";
        public static final String BG_CYAN = "\u001B[46m";
    }

    public static void ask(String massage) {
        System.out.println(ConsoleColors.BRIGHT_PURPLE + "[:D] " + massage + ConsoleColors.RESET);
    }

    public static void printSuccess(String massage) {
        System.out.println(ConsoleColors.GREEN + "[SUCCESS] " + massage + ConsoleColors.RESET);
    }

    public static void printInfo(String massage) {
        System.out.println(ConsoleColors.DIM + ConsoleColors.GREEN + "[INFORMATION] " + massage + ConsoleColors.RESET);
    }

    public static void printError(String massage) {
        System.out.println(ConsoleColors.RED + "[ERROR] " + massage + ConsoleColors.RESET);
    }

    public static void option(int index, String massage) {
        System.out.println(ConsoleColors.DIM + ConsoleColors.YELLOW + "         ▻ " + index +
                ". " + massage + ConsoleColors.RESET);
    }

    public static void upperBorder(String massage) {
        System.out.println(ConsoleColors.CYAN + "◸—————     ⁂" + massage + "⁂     —————◹" + ConsoleColors.RESET);
    }

    public static void miniUpperBorder(String massage) {
        System.out.println(ConsoleColors.CYAN + "◸———  ⁎◊" + massage + "◊⁎  ———◹" + ConsoleColors.RESET);
    }

    public static void lowerBorder(String massage) {
        int spaces = massage.length();
        System.out.print(ConsoleColors.CYAN + "◺——————");
        for (int i = 0; i < spaces + 10; i++) {
            System.out.print("—");
        }
        System.out.println("——————◿" + ConsoleColors.RESET);
    }

    public static void miniLowerBorder(String massage) {
        int spaces = massage.length();
        System.out.print(ConsoleColors.CYAN + "◺——");
        for (int i = 0; i < spaces + 8; i++) {
            System.out.print("—");
        }
        System.out.println("———◿" + ConsoleColors.RESET);
    }

    public static Map<String, String> askForInformation(String user) {
        String[] admin = new String[]{"name", "username", "password"};
        String[] customer = new String[]{"firstname", "lastname", "email", "phone number", "password"};
        String[] seller = new String[]{"firstname", "lastname", "ID", "state", "phone number", "password"};
        HashMap<String, String> information = new HashMap<>();

        switch (user.toLowerCase()) {
            case "admin" -> {
                for (int i = 0; i < admin.length; i++) {
                    option(i + 1, admin[i]);
                    String data = ScannerWrapper.nextLine();
                    information.put(admin[i], data);
                }
            }
            case "customer" -> {
                for (int i = 0; i < customer.length; i++) {
                    option(i + 1, customer[i]);
                    String data = ScannerWrapper.nextLine();
                    information.put(customer[i], data);
                }
            }
            case "seller" -> {
                for (int i = 0; i < seller.length; i++) {
                    option(i + 1, seller[i]);
                    String data = ScannerWrapper.nextLine();
                    information.put(seller[i], data);
                }
            }
            default -> {
                return null;
            }
        }
        return information;
    }

    public static void newLine() {
        System.out.println("\n");
    }

    public static void option(int num){
        System.out.println( "\n\n         ▻ " + num + ". ");
    }

    public static void printFancyTable(Map<String, String> info) {
        if (info == null || info.isEmpty()) {
            printError("No info to display.");
            return;
        }

        int keyWidth = "Field".length();
        int valWidth = "Value".length();
        for (var entry : info.entrySet()) {
            keyWidth = Math.max(keyWidth, entry.getKey().length());
            valWidth = Math.max(valWidth, entry.getValue().length());
        }
        keyWidth += 2;
        valWidth += 2;

        String top = "╔" + "═".repeat(keyWidth) + "╦" + "═".repeat(valWidth) + "╗";
        String mid = "╠" + "═".repeat(keyWidth) + "╬" + "═".repeat(valWidth) + "╣";
        String bot = "╚" + "═".repeat(keyWidth) + "╩" + "═".repeat(valWidth) + "╝";

        System.out.println(top);
        System.out.printf("║%s║%s║\n",
                ConsoleColors.BRIGHT_BLUE + centerText("Field", keyWidth) + ConsoleColors.RESET,
                ConsoleColors.BRIGHT_BLUE + centerText("Value", valWidth) + ConsoleColors.RESET);
        System.out.println(mid);

        for (var entry : info.entrySet()) {
            System.out.printf("║%s║%s║\n",
                    centerText(entry.getKey(), keyWidth),
                    centerText(entry.getValue(), valWidth));
        }

        System.out.println(bot);
    }


}

