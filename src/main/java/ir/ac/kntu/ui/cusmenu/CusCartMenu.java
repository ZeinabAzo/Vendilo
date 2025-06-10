package ir.ac.kntu.ui.cusmenu;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.models.Address;
import ir.ac.kntu.models.Cart;
import ir.ac.kntu.util.InputHelper;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

public class CusCartMenu {

    private CusControl cusControl;

    public CusCartMenu(CusControl cusControl) {
        this.cusControl = cusControl;
    }

    public void showAllCarts() {
        while (true) {
            PrintHelper.option(1, "See all carts");
            PrintHelper.option(2, "return");
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> {
                    int index = SplitDisplay.show(cusControl.getCustomer().getCarts());
                    if (index < 0 || index >= cusControl.getCustomer().getCarts().size()) {
                        PrintHelper.printError("Invalid cart selected or operation canceled.");
                        return;
                    }
                    showCart(cusControl.getCustomer().getCart(index));
                }
                case 2 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid option!");

            }
        }
    }

    private void showCart(Cart cart) {
        PrintHelper.ask("What would you want to do with this cart?");
        PrintHelper.option(1, "purchase it");
        PrintHelper.option(2, "delete it");
        PrintHelper.option(3, "enhance it");
        PrintHelper.option(3, "nothing, I was just visiting");
        int choice = ScannerWrapper.nextInt();
        switch (choice) {
            case 1 -> purchaseCart(cart);
            case 2 -> cusControl.deleteCart(cart);
            case 3 -> cusControl.enhanceCart(cart);
            case 4 -> {
            }
            default -> PrintHelper.printError("Invalid choice");
        }
    }


    private void purchaseCart(Cart cart) {
        PrintHelper.option(1, "Choose an existing address");
        PrintHelper.option(2, "Insert an address");
        PrintHelper.option(3, "Return");

        int choice = ScannerWrapper.nextInt();
        Address address = null;

        switch (choice) {
            case 1 -> address = chooseExistingAddress();
            case 2 -> address = insertAddress();
            case 3 -> {
                return;
            }
            default -> PrintHelper.printError("Invalid option!");
        }

        if(address != null){
            cusControl.purchaseCart(address, cart);
        }
    }

    private Address chooseExistingAddress() {
        if (cusControl.getCustomer().getAddresses().isEmpty() || cusControl.getCustomer().getAddresses() == null){
            PrintHelper.printError("You've got no address saved darling please insert one.");
            return null;
        }
        int choice = SplitDisplay.show(cusControl.getCustomer().getAddresses());
        if (choice < 0 || choice > cusControl.getCustomer().getAddresses().size()) {
            PrintHelper.printError("Your choice is imaginary(i) (❁´◡`❁)");
            return null;
        }
        return cusControl.getCustomer().getAddresses().get(choice);
    }

    private Address insertAddress() {
        Address address = null;
        while (address == null){
            address = InputHelper.newAddress();
            cusControl.getCustomer().addAddress(address);
        }
        return address;
    }

}
