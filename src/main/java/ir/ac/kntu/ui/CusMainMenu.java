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

    private void addressOptions() {
        while (true) {
            PrintHelper.miniUpperBorder("  Address display  ");
            PrintHelper.option(1, "Show all addresses");
            PrintHelper.option(2, "Insert a new address");
            PrintHelper.option(3, "Return");
            PrintHelper.miniLowerBorder("  Address display  ");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> showAddresses();
                case 2 -> insertAddress();
                case 3 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid option!");
            }
        }
    }

    private void insertAddress() {
        // TODO
        PrintHelper.printInfo("Inserting address...");
    }

    private void showAddresses() {
        // TODO
        PrintHelper.printInfo("Displaying all addresses...");
    }

    private void showAllCarts() {
        CusCartMenu cusCartMenu = new CusCartMenu(ccusControl);
        cusCartMenu.showAllCarts();
    }

    private void searchOptions() {
        CusSearchMenu customerSearchMenu = new CusSearchMenu(ccusControl);
        customerSearchMenu.firstPage();
    }

    private void walletMenu() {
        while (true) {
            PrintHelper.miniUpperBorder(" $  Hi, I'm your wallet!  $");
            PrintHelper.option(1, "Show former transactions");
            PrintHelper.option(2, "Charge Balance");
            PrintHelper.option(3, "Return");
            PrintHelper.miniLowerBorder(" $  Hi, I'm your wallet!  $");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> showTransactions();
                case 2 -> chargeBalance();
                case 3 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid option!");
            }
        }
    }

    private void showTransactions() {

        PrintHelper.printInfo("Showing transactions...");
    }

    private void chargeBalance() {

        PrintHelper.printInfo("Charging wallet...");
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
