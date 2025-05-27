package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class CusMainMenu {

    private CusControl ccusControl;

    public CusMainMenu(CusControl cusControl) {
        this.ccusControl = cusControl;
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
        CusWalletM cusWalletM = new CusWalletM(ccusControl);
        cusWalletM.walletMenu();
    }

    private void addressOptions() {
        AddressCus addressCus = new AddressCus(ccusControl);
        addressCus.firstShow();
    }

    private void showAllCarts() {
        CusCartMenu cusCartMenu = new CusCartMenu(ccusControl);
        cusCartMenu.showAllCarts();
    }

    private void searchOptions() {
        CusSearchMenu customerSearchMenu = new CusSearchMenu(ccusControl);
        customerSearchMenu.firstPage();
    }

    private void orders() {
        // Show purchased carts in a loop if needed
        PrintHelper.miniUpperBorder("Choose a purchased cart");
        //Show orders
        PrintHelper.miniLowerBorder("Purchased carts");
    }

    private void setting() {
        // User can update info
        //implement setting update
        PrintHelper.printInfo("User settings page.");
    }

    private void support() {
        //implement support UI
        PrintHelper.printInfo("Support page.");
    }
}
