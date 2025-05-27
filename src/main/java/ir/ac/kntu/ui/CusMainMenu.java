package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

public class CusMainMenu {

    private CusControl cusControl;

    public CusMainMenu(CusControl cusControl) {
        this.cusControl = cusControl;
    }

    public void showPage() {
        while (true) {
            PrintHelper.upperBorder("Customer profile");
            PrintHelper.option(1, "Search products");
            PrintHelper.option(2, "Carts");
            PrintHelper.option(3, "Addresses");
            PrintHelper.option(4, "Wallet");
            PrintHelper.option(5, "Orders");
            PrintHelper.option(6, "Setting");
            PrintHelper.option(7, "Support");
            PrintHelper.option(8, "Return");
            PrintHelper.lowerBorder("Welcome dear customer");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> searchOptions();
                case 2 -> showAllCarts();
                case 3 -> addressOptions();
                case 4 -> walletMenu();
                case 5 -> orders();
                case 6 -> setting();
                case 7 -> support();
                case 8 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid option! Try again.");
            }
        }
    }

    private void walletMenu() {
        CusWalletM cusWalletM = new CusWalletM(cusControl);
        cusWalletM.walletMenu();
    }

    private void addressOptions() {
        AddressCus addressCus = new AddressCus(cusControl);
        addressCus.firstShow();
    }

    private void showAllCarts() {
        CusCartMenu cusCartMenu = new CusCartMenu(cusControl);
        cusCartMenu.showAllCarts();
    }

    private void searchOptions() {
        CusSearchMenu customerSearchMenu = new CusSearchMenu(cusControl);
        customerSearchMenu.firstPage();
    }

    private void orders() {
        PrintHelper.miniUpperBorder("Choose a purchased cart");
        cusControl.showPurchCart();
    }

    private void setting() {

        PrintHelper.printInfo(cusControl.getCustomer().toString());
        PrintHelper.ask("Do you want to change anything?");
        PrintHelper.option(1, "yes, let's change some things ");
        PrintHelper.option(2, "Nope, return");
        PrintHelper.option(3, "exit");
        int choice = ScannerWrapper.nextInt();

        switch (choice) {
            case 1 -> editSomething();
            case 2 -> {
            }
            case 3 -> Exit.exit();
            default -> PrintHelper.printError("Invalid command");
        }
    }

    private void editSomething() {
        boolean goOn = true;
        do{
            PrintHelper.ask("what to change?");
            PrintHelper.option(1, "first name");
            PrintHelper.option(2, "last name");
            PrintHelper.option(3, "email");
            PrintHelper.option(4, "password");
            goOn=false;
        }while (goOn);
    }

    private void support() {
        //implement support UI
        PrintHelper.printInfo("Support page.");
    }
}
