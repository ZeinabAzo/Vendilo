package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CustomerController;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.HashMap;

public class CustomerMainMenu {

    private CustomerController customerController;

    public CustomerMainMenu(CustomerController customerController){
        this.customerController=customerController;
    }

    public void showPage(){

        PrintHelper.upperBorder("Customer profile");
        PrintHelper.option(1, "Search products");
        PrintHelper.option(2, "Cart");
        PrintHelper.option(3, "Addresses");
        PrintHelper.option(4, "Wallet");
        PrintHelper.option(5, "Orders");
        PrintHelper.option(6, "Setting");
        PrintHelper.option(7, "Support");
        PrintHelper.option(8, "return");
        PrintHelper.lowerBorder("Welcome dear admin");
        int choice= ScannerWrapper.nextInt();

        switch (choice){
            case 1 -> searchOptions();
            case 2 -> cartMenu();
        }

    }

    private void searchOptions(){
        CustomerSearchMenu customerSearchMenu=new CustomerSearchMenu(customerController);
        customerSearchMenu.firstPage();
    }

    private void cartMenu(){

        PrintHelper.miniUpperBorder("Customer cart:");
        PrintHelper.option(1, "Show cart");
        PrintHelper.option(2, "Purchase cart");
        PrintHelper.miniLowerBorder("Customer cart:");
        int choice = ScannerWrapper.nextInt();

        switch (choice){
            case 1 -> {

            }
        }


    }

    private void purchaseCart(){
        PrintHelper.option(1, "Choose an existing address");
        PrintHelper.option(2, "Insert an address");
    }

    private void addressOptions(){
        PrintHelper.miniUpperBorder("  Address display  ");
        PrintHelper.option(1, "Show all addresses");
        PrintHelper.option(2, "Insert a new address");
        PrintHelper.miniLowerBorder("  Address display  ");
    }

    private void walletMenu(){

        PrintHelper.miniUpperBorder(" $  Hi, I'm your wallet!  $");
        PrintHelper.option(1, "Show former transactions");
        PrintHelper.option(2, "Charge Balance");
        PrintHelper.miniLowerBorder(" $  Hi, I'm your wallet!  $");


    }

    private void orders(){
        PrintHelper.miniUpperBorder("Choose a purchased cart");

    }

    private void setting(){
        //String fName, String lName, String email, String phoneNumber, String password
    }

    private void support(){

    }



}
