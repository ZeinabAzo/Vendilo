package ir.ac.kntu.util;

public class PrintHelper {

    public static class consoleColors {

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

    public static void printAnnouncement(String message){
        System.out.println(consoleColors.BRIGHT_PURPLE + "[Announcement] " + message + consoleColors.RESET);
    }

    public static void printSuccess(String message) {
        System.out.println(consoleColors.GREEN + "[SUCCESS] " + message + consoleColors.RESET);
    }

    public static void printError(String message) {
        System.out.println(consoleColors.RED + "[ERROR] " + message + consoleColors.RESET);
    }
}
