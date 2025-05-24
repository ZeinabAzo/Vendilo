package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.SellerController;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class SellerMainMenu {

    private SellerController sellerController;

    public SellerMainMenu(SellerController sellerController){
        this.sellerController=sellerController;
    }

    public void showPage(){
        PrintHelper.upperBorder("Seller profile");
        PrintHelper.option(1, "Products");
        PrintHelper.option(2, "Wallet");
        PrintHelper.option(3, "Orders");
        PrintHelper.option(4, "return");
        PrintHelper.lowerBorder("Seller profile");
        int choice= ScannerWrapper.nextInt();

        switch (choice){
            case 1 -> {
            }

        }

    }

    public void productOptions(){
        PrintHelper.upperBorder("HAHA This is your stock");
        PrintHelper.option(1, "Show all products");
        PrintHelper.option(2, "Add new product");
        PrintHelper.lowerBorder("HAHA This is your stock");
        int choice=ScannerWrapper.nextInt();

        switch (choice){
            case 1 -> showProducts();
            case 2 -> {
                //
            }
        }

    }

    public void showProducts() {
        while (true) {
            Product product = sellerController.showProducts();
            if (product == null) {
                PrintHelper.printError("Invalid selection. Please try again.");
                return;
            }

            PrintHelper.ask("What would you like to do with this product?");
            PrintHelper.option(1, "Add Item to Inventory");
            PrintHelper.option(2, "Remove Item from Inventory");
            PrintHelper.option(3, "Return to Previous Menu");
            int choice = ScannerWrapper.nextInt();

            if (choice == 3) {
                return;
            }

            if (choice != 1 && choice != 2) {
                PrintHelper.printError("Invalid choice. Please select a valid option.");
                continue;
            }

            PrintHelper.ask("Enter the number of items to " + (choice == 1 ? "add" : "remove") + ":");
            int count = ScannerWrapper.nextInt();
            if (choice == 2) {
                count = -count;
            }

            boolean success = sellerController.changeInventory(count, product);
            if (success) {
                PrintHelper.printSuccess("Inventory updated successfully.");
            } else {
                PrintHelper.printError("Operation failed. Please try again.");
            }

            PrintHelper.ask("Would you like to perform another action on a product?");
            PrintHelper.option(1, "Yes");
            PrintHelper.option(2, "No, return to previous menu");
            int again = ScannerWrapper.nextInt();
            if (again != 1) {
                break;
            }
        }
    }

    public void addProduct(){

    }

}
