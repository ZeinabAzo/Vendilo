package ir.ac.kntu.util;

import ir.ac.kntu.enums.ReportType;
import java.util.ArrayList;
import java.util.List;

public class RTypeSelector {


    public static List<ReportType> chooseReportTypes() {
        ReportType[] allTypes = ReportType.values();
        List<ReportType> types = new ArrayList<>();

        while (true) {
            PrintHelper.printInfo("\nAvailable Report Types:");
            for (int i = 0; i < allTypes.length; i++) {
                System.out.printf("%d) %s%n", i + 1, formatEnumName(allTypes[i]));
            }
            PrintHelper.printInfo("0) Done selecting");

            PrintHelper.ask("Choose a report type by number: ");
            int choice = ScannerWrapper.nextInt();


            if (choice == 0) {
                break; // Exit loop
            }

            if (choice < 1 || choice > allTypes.length) {
                PrintHelper.printError("Invalid choice. Try again.");
                continue;
            }

            ReportType selected = allTypes[choice - 1];
            if (types.contains(selected)) {
                PrintHelper.printError("You already selected this type.");
            } else {
                types.add(selected);
                PrintHelper.printInfo(selected + " added.");
            }
        }

        return types;
    }

    public static String formatEnumName(ReportType type) {
        String name = type.name().toLowerCase().replace('_', ' ');
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
