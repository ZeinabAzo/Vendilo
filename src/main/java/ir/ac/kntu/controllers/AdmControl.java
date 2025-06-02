package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.AuthRequest;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Order;
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

    public AdmControl(AdminDB adminDB, CustomerDB customerDB, Admin admin) {
        this.adminDB = adminDB;
        this.customerDB = customerDB;
        this.admin = admin;
    }

    public AdminDB getAdminDB() {
        return adminDB;
    }


    public int showAuthREq() {
        boolean goOn = true;
        List<AuthRequest> authRequests = new ArrayList<>();

        while (goOn) {
            PrintHelper.option(1, "show all auth requests");
            PrintHelper.option(2, "show answered auth requests");
            PrintHelper.option(3, "show unanswered auth requests");
            PrintHelper.option(4, "return");
            PrintHelper.option(5, "exit");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> authRequests = adminDB.getAuthRequest();
                case 2 -> authRequests = adminDB.getAuthRequest().stream()
                        .filter(a -> a.getResponse() == null).toList();
                case 3 -> authRequests = adminDB.getAuthRequest().stream()
                        .filter(a -> a.getResponse() != null).toList();
                case 4 -> goOn = false;
                case 5 -> Exit.exit();
                default -> PrintHelper.printError("Invalid command");
            }

            if (choice >= 0 && choice <= authRequests.size()) {
                return SplitDisplay.show(authRequests);
            }
        }
        return 0;
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
        boolean goOn = true;
        int choice = ScannerWrapper.nextInt();

        while (goOn) {
            switch (choice) {
                case 1 -> showSellRep();
                case 2 -> showCusRep();
                case 3 -> goOn = false;
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

        for (Customer c : customerDB.getCustomers()) {
            orders.addAll(c.getPurchOrders());
        }

        return orders;
    }

    public List<Order> getByEmail(String email) {
        List<Order> orders = new ArrayList<>();

        for (Customer c : customerDB.getCustomers()) {
            if (email.equals(c.getEmail())) {
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