package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Order;
import ir.ac.kntu.services.SearchProducts;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdmControl {

    private AdminDB adminDB;
    private Admin admin;
    private CustomerDB customerDB;
    private SellerDB sellerDB;
    private ProductDB productDB;
    private SearchProducts searchProducts;

    public AdmControl(AdminDB adminDB, ProductDB productDB,CustomerDB customerDB, SellerDB sellerDB, Admin admin) {
        this.adminDB = adminDB;
        this.productDB = productDB;
        this.customerDB=customerDB;
        this.sellerDB=sellerDB;
        this.admin = admin;
    }

    public AdminDB getAdminDB() {
        return adminDB;
    }

    public void setServices() {//add necessary services
        searchProducts = new SearchProducts(productDB);
    }


    public int showAuthREq() {
        return SplitDisplay.show(adminDB.getAuthRequest());
    }

    public Admin getAdmin() {
        return admin;
    }

    public void sendAuthRes(int choice) {
        boolean goOn = true;
        boolean accept = false;

        while (goOn) {
            PrintHelper.ask("Do you accept this request? (yes / no / return)");
            String input = ScannerWrapper.nextLine().trim().toLowerCase();


            switch (input) {
                case "yes" -> {
                    accept = true;
                    goOn = false;
                }
                case "no" -> goOn = false;
                case "return" -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid input. Please enter 'yes', 'no', or 'return'.");
            }
        }
        adminDB.getAuthRequest().get(choice).setAccepted(accept);
        PrintHelper.ask("Please enter your response");
        adminDB.getAuthRequest().get(choice).setResponse(ScannerWrapper.nextLine());

    }

    public void showReports() {
        PrintHelper.option(1, "seller's reports");
        PrintHelper.option(2, "customer's reports");
        PrintHelper.option(3, "return");
        PrintHelper.option(4, "exit");
        boolean goOn=true;
        int choice = ScannerWrapper.nextInt();

        while(goOn){
            switch (choice){
                case  1 -> showSellRep();
                case 2 -> showCusRep();
                case 3 -> goOn=false;
                case 4 -> Exit.exit();
                default -> PrintHelper.printError("Invalid command");
            }
        }
    }

    private void showSellRep() {
        int index = SplitDisplay.show(adminDB.getSellerCompliant());

        if (index == -1) {
            PrintHelper.printError("No complaints to show.");
            return;
        }

        PrintHelper.ask("Enter your response (or type 'return' or 'exit'):");
        String message = ScannerWrapper.nextLine().trim().toLowerCase();

        switch (message) {
            case "return" -> {
            }
            case "exit" -> Exit.exit();
            default -> {
                adminDB.findSellComp(index).setResponse(message);
                PrintHelper.printSuccess("Response recorded successfully.");
            }
        }
    }

    private void showCusRep() {
        int index = SplitDisplay.show(adminDB.getCusComplaint());

        if (index == -1) {
            PrintHelper.printError("No complaints to show.");
            return;
        }

        PrintHelper.ask("Enter your response (or type 'return' or 'exit'):");
        String message = ScannerWrapper.nextLine().trim().toLowerCase();

        switch (message) {
            case "return" -> {
            }
            case "exit" -> Exit.exit();
            default -> {
                adminDB.findCusComp(index).setResponse(message);
                PrintHelper.printSuccess("Response recorded successfully.");
            }
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        for (Customer c : customerDB.getCustomers()){
            orders.addAll(c.getPurchOrders());
        }

        return orders;
    }

    public List<Order> getByEmail(String email) {
        List<Order> orders = new ArrayList<>();

        for (Customer c : customerDB.getCustomers()){
            if(email.equals(c.getEmail())){
                orders.addAll(c.getPurchOrders());
                break;
            }
        }

        return orders;
    }

    public List<Order> getByDate(LocalDate start, LocalDate end) {
        List<Order> orders = getAllOrders();

        orders.removeIf(o -> o.getOrderDate().isBefore(start) || o.getOrderDate().isAfter(end));

        return orders;
    }
}