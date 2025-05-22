package ir.ac.kntu.util;

import java.util.HashMap;

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

    public static void printAnnouncement(String massage){
        System.out.println(consoleColors.BRIGHT_PURPLE + "[Announcement] " + massage + consoleColors.RESET);
    }

    public static void printSuccess(String massage) {
        System.out.println(consoleColors.GREEN + "[SUCCESS] " + massage + consoleColors.RESET);
    }

    public static void printError(String massage) {
        System.out.println(consoleColors.RED + "[ERROR] " + massage + consoleColors.RESET);
    }

    public static void option(int index, String massage){
        System.out.println(consoleColors.DIM+ consoleColors.YELLOW  +"         ▻ "+ index +
                ". " + massage + consoleColors.RESET );
    }

    public static void upperBorder(String massage){
        System.out.println(consoleColors.BRIGHT_BLUE + "◸—————    ⁂" + massage +"⁂    ————◹"+ consoleColors.RESET);
    }

    public static void miniUpperBorder(String massage){
        System.out.println(consoleColors.BRIGHT_BLUE + "◸———  ⁎◊" + massage +"◊⁎  ———◹"+ consoleColors.RESET);
    }

    public static void lowerBorder(String massage){
        int spaces= massage.length();
        System.out.print(consoleColors.BRIGHT_BLUE + "◺—————");
        for (int i=0; i<spaces+10; i++){
            System.out.print("—");
        }
        System.out.println("—————◿" +consoleColors.RESET);
    }

    public static void miniLowerBorder(String massage){
        int spaces= massage.length();
        System.out.print(consoleColors.BRIGHT_BLUE + "◺——");
        for (int i=0; i<spaces+8; i++){
            System.out.print("—");
        }
        System.out.println("——◿" +consoleColors.RESET);
    }

    public static HashMap<String, String> askForInformation(String user){
        String[] admin= new String[]{"name", "username", "password"};
        String[] customer= new String[]{"firstname", "lastname", "email", "phone number", "password"};
        String[] seller= new String[]{"firstname", "lastname", "ID", "state", "phone number", "password"};
        HashMap<String, String> information=new HashMap<>();

        switch (user.toLowerCase()){
            case "admin" -> {
                for(int i=0; i<admin.length; i++){
                    option(i+1, admin[i]);
                    String data= ScannerWrapper.nextLine();
                    information.put(admin[i], data);
                }
            }
            case "customer" -> {
                for(int i=0; i<customer.length; i++){
                    option(i+1, customer[i]);
                    String data= ScannerWrapper.nextLine();
                    information.put(customer[i], data);
                }
            }
            case "seller" -> {
                for(int i=0; i<seller.length; i++){
                    option(i+1, seller[i]);
                    String data= ScannerWrapper.nextLine();
                    information.put(seller[i], data);
                }
            }
            default -> {
                return null;
            }
        }
        return information;
    }

}

