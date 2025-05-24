package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CustomerController;
import ir.ac.kntu.models.Cart;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

import java.util.HashMap;

public class CustomerMainMenu {

    private CustomerController customerController;

    public CustomerMainMenu(CustomerController customerController){
        this.customerController = customerController;
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

    private void searchOptions() {
        CustomerSearchMenu customerSearchMenu = new CustomerSearchMenu(customerController);
        customerSearchMenu.firstPage();
    }

    private void showAllCarts(){
        while(true) {
            PrintHelper.option(1, "See all carts");
            PrintHelper.option(2, "return");
            int choice=ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> {
                    int index = SplitDisplay.show(customerController.getCustomer().getCarts());
                    if (index == -1 || index >= customerController.getCustomer().getCarts().size()) {
                        PrintHelper.printError("Invalid cart selected or operation canceled.");
                        return;
                    }
                    cartMenu(customerController.getCustomer().getCart(index));
                }
                case 2 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid option!");

            }
        }
    }

    private void cartMenu(Cart cart) {
        while (true) {
            PrintHelper.miniUpperBorder("Customer cart:");
            PrintHelper.option(1, "Show cart");
            PrintHelper.option(2, "Purchase cart");
            PrintHelper.option(3, "Return");
            PrintHelper.miniLowerBorder("Customer cart:");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> showCart();
                case 2 -> purchaseCart();
                case 3 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid option!");
            }
        }
    }

    private void showCart() {
        // TODO: implement cart display
        PrintHelper.printSuccess("Cart shown here.");
    }

    private void purchaseCart() {
        while (true) {
            PrintHelper.option(1, "Choose an existing address");
            PrintHelper.option(2, "Insert an address");
            PrintHelper.option(3, "Return");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> chooseExistingAddress(); // implement this
                case 2 -> insertAddress(); // implement this
                case 3 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid option!");
            }
        }
    }

    private void chooseExistingAddress() {
        // TODO
        PrintHelper.printInfo("Choosing address...");
    }

    private void insertAddress() {
        // TODO
        PrintHelper.printInfo("Inserting address...");
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

    private void showAddresses() {
        // TODO
        PrintHelper.printInfo("Displaying all addresses...");
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
