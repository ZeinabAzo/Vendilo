package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.AdminController;
import ir.ac.kntu.controllers.Navigate;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class AdminMainMenu {

    private AdminController adminController;

    public AdminMainMenu(AdminController adminController){
        this.adminController=adminController;
    }

    public void showPage(){
        PrintHelper.upperBorder("Welcome dear admin");
        PrintHelper.option(1, "Authentication requests");
        PrintHelper.option(2, "help requests");
        PrintHelper.option(3, "orders");
        PrintHelper.option(4, "return");
        PrintHelper.lowerBorder("Welcome dear admin");
        int choice= ScannerWrapper.nextInt();

        switch (choice){
            case 1 -> {

            }

        }

    }
}
