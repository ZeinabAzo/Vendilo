package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.models.Address;
import ir.ac.kntu.util.*;

public class AddressCus {

    private CusControl cusControl;

    public AddressCus(CusControl cusControl) {
        this.cusControl = cusControl;
    }

    public void firstShow(){
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
        try {
            Address address = InputHelper.newAddress();
            cusControl.addAddress(address);
            PrintHelper.printInfo("Inserting address...");
        } catch ( Exception e){
            PrintHelper.printError("We couldn't add your address sorry");
        }
    }

    private void showAddresses() {
        int choice = SplitDisplay.show(cusControl.getCustomer().getAddresses());

        if (choice>=0){
            PrintHelper.option(1, "Delete address");
            PrintHelper.option(2, "Edit address");
            PrintHelper.option(3, "return");
            PrintHelper.option(4, "exit");
            int option = ScannerWrapper.nextInt();

            switch (option){
                case 1 -> deleteAddress(choice);
                case 2 -> editAddress(choice);
                case 3 -> {
                }
                case 4 -> Exit.exit();
                default -> PrintHelper.printError("Invalid command psycho :| ");
            }
        }
    }

    private void editAddress(int index) {
        int choice;
        do {

            PrintHelper.ask("What do you want to change?");
            PrintHelper.option(1, "title");
            PrintHelper.option(2, "state");
            PrintHelper.option(3, "city");
            PrintHelper.option(4, "description");
            PrintHelper.option(5, "return");
            choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> editTitle(index);
                case 2 -> editState(index);
                case 3 -> editCity(index);
                case 4 -> editDescription(index);
                case 5 -> {
                }
                default -> PrintHelper.printError("Invalid command, you bore me dummy");
            }
        } while (choice != 5);
    }

    private void editDescription(int index) {
        cusControl.editDescription(index);
    }

    private void editCity(int index) {
        cusControl.editCity(index);
    }

    private void editState(int index) {
        cusControl.editState(index);
    }

    private void editTitle(int index) {
        cusControl.editTitle(index);
    }

    private void deleteAddress(int index) {
        cusControl.deleteAddress(index);
        PrintHelper.printSuccess("Deleted successfully");
    }
}
