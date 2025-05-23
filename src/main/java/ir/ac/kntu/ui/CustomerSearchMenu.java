package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CustomerController;
import ir.ac.kntu.models.*;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerSearchMenu {

    private CustomerController customerController;

    public CustomerSearchMenu(CustomerController customerController){
        this.customerController=customerController;
    }

    public void firstPage(){
        PrintHelper.miniUpperBorder("Set needed filters :");
        PrintHelper.option(1, "Product name");
        PrintHelper.option(2, "Product type");
        PrintHelper.option(3,"Price range");
        PrintHelper.miniLowerBorder("Set needed filters :");
        int choice= ScannerWrapper.nextInt();

        switch (choice){
            case 1 -> searchByName();
            case 2 ->
        }
    }

    private void searchByName(){

        PrintHelper.ask("Enter the product name:");
        String name=ScannerWrapper.nextLine();
        HashMap<Seller, Product> filtered= customerController.searchByName(name);
        List<Product> productList = new ArrayList<>(filtered.values());
        showProducts(productList);
    }

    private void showProducts(List<Product> productList){
        int chosen=SplitDisplay.show(productList);
        showProduct(productList.get(chosen));
        PrintHelper.option(1, "Add to cart");
        PrintHelper.option(2, "return");
        int choice=ScannerWrapper.nextInt();

        switch (choice){
            case 1 -> {
                customerController.orderProduct(productList.get(chosen));
            }
            case 2 -> {
            }
        }
    }

    private void showProduct(Product product) {
        //show fields and details
    }

    private void searchByType(){
        PrintHelper.ask("Enter the product type:");
        String type=ScannerWrapper.nextLine();
        HashMap<Seller, Product> filtered= customerController.searchByName(type);
        List<Product> productList = new ArrayList<>(filtered.values());
        showProducts(productList);
    }

    private void priceRangeOptions(){
        PrintHelper.option(1, "Product name");
        PrintHelper.option(2, "Product type");
        PrintHelper.option(3, "Product name and product type");
        int choice=ScannerWrapper.nextInt();

        switch (choice){

        }
    }
}
