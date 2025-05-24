package ir.ac.kntu.util;

import java.util.Arrays;

public class InputHelper {
    public static <E extends Enum<E>> E inputEnum(String prompt, Class<E> enumClass) {
        PrintHelper.ask(prompt + " " + Arrays.toString(enumClass.getEnumConstants()));
        try {
            return Enum.valueOf(enumClass, ScannerWrapper.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            PrintHelper.printError("Invalid input for " + enumClass.getSimpleName());
            return null;
        }
    }

    public static boolean inputYesNo(String prompt) {
        PrintHelper.ask(prompt + " (yes/no)");
        String answer = ScannerWrapper.nextLine().trim().toLowerCase();
        return answer.equals("yes");
    }

    public static String inputString(String prompt) {
        PrintHelper.ask(prompt);
        return ScannerWrapper.nextLine();
    }

    public static int inputInt(String prompt) {
        PrintHelper.ask(prompt);
        return ScannerWrapper.nextInt();
    }

    public static double inputDouble(String prompt) {
        PrintHelper.ask(prompt);
        return ScannerWrapper.nextDouble();
    }

}
