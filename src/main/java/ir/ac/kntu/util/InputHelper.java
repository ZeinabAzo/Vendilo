package ir.ac.kntu.util;

import ir.ac.kntu.models.Address;

import java.util.Arrays;

import static ir.ac.kntu.util.PrintHelper.ask;

public class InputHelper {
    public static <E extends Enum<E>> E inputEnum(String prompt, Class<E> enumClass) {
        ask(prompt + " " + Arrays.toString(enumClass.getEnumConstants()));
        try {
            return Enum.valueOf(enumClass, ScannerWrapper.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            PrintHelper.printError("Invalid input for " + enumClass.getSimpleName());
            return null;
        }
    }

    public static boolean inputYesNo(String prompt) {
        ask(prompt + " (yes/no)");
        String answer = ScannerWrapper.nextLine().trim().toLowerCase();
        return "yes".equals(answer);
    }

    public static String inputString(String prompt) {
        ask(prompt);
        return ScannerWrapper.nextLine();
    }

    public static int inputInt(String prompt) {
        ask(prompt);
        return ScannerWrapper.nextInt();
    }

    public static double inputDouble(String prompt) {
        ask(prompt);
        return ScannerWrapper.nextDouble();
    }

    public static Address newAddress(){
        ask("Enter title:");
        String title=ScannerWrapper.nextLine();
        ask("Enter state:");
        String state=ScannerWrapper.nextLine();
        ask("Enter city:");
        String city=ScannerWrapper.nextLine();
        ask("Enter description (if needed) :");
        String description=ScannerWrapper.nextLine();

        return new Address(title, state, city, description);
    }

}
