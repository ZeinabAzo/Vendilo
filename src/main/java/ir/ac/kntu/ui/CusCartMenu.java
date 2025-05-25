package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CustomerController;
import ir.ac.kntu.models.Cart;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

public class CusCartMenu {

    CustomerController customerController;

    public CusCartMenu(CustomerController customerController){
        this.customerController=customerController;
    }

    public void showAllCarts(){
        while(true) {
            PrintHelper.option(1, "See all carts");
            PrintHelper.option(2, "return");
            int choice= ScannerWrapper.nextInt();

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

}
