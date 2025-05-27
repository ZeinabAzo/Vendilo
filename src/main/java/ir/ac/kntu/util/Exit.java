package ir.ac.kntu.util;

public class Exit {

    public static void exit() {
        PrintHelper.ask("Are you sure you want to exit? (yes/hell no)");
        String confirm = ScannerWrapper.nextLine().toLowerCase();
        if (confirm.equals("yes") || confirm.equals("y")) {
            System.exit(0);
        }
    }

}
