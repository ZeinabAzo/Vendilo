package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.AdmControl;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminMainMenu {

    private AdmControl adminController;

    public AdminMainMenu(AdmControl adminController) {
        this.adminController = adminController;
    }

    public void showPage() {
        PrintHelper.upperBorder("Welcome dear admin");
        PrintHelper.option(1, "Authentication requests");
        PrintHelper.option(2, "Reports");
        PrintHelper.option(3, "Orders");
        PrintHelper.option(4, "return");
        PrintHelper.lowerBorder("Welcome dear admin");
        int choice = ScannerWrapper.nextInt();

        switch (choice) {
            case 1 -> {

            }

        }

    }
}
