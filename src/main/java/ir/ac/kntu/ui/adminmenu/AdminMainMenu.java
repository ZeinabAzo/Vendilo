package ir.ac.kntu.ui.adminmenu;

import ir.ac.kntu.controllers.AdmControl;
import ir.ac.kntu.models.*;
import ir.ac.kntu.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AdminMainMenu {

    private AdmControl adminController;

    public AdminMainMenu(AdmControl adminController) {
        this.adminController = adminController;
    }

    public void showPage() {
        boolean goON = true;
        while (goON){
            PrintHelper.upperBorder("Welcome dear admin");
            PrintHelper.option(1, "Authentication requests");
            PrintHelper.option(2, "Reports");
            PrintHelper.option(3, "Orders");
            PrintHelper.option(4, "return");
            PrintHelper.lowerBorder("Welcome dear admin");
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> showAuthRequest();
                case 2 -> showReports();
                case 3 -> showOrders();
                case 4 -> {
                    goON = false;
                }
                default -> PrintHelper.printError("Invalid command");
            }
        }
    }

    private void showOrders() {
        boolean goOn = true;

        while (goOn) {
            PrintHelper.upperBorder("Let's explore people's business weirdo ...");
            PrintHelper.option(1, "see all orders");
            PrintHelper.option(2, "filter by customer-email");
            PrintHelper.option(3, "filter by date");
            PrintHelper.option(4, "return");
            PrintHelper.option(5, "exit");
            int choice = ScannerWrapper.nextInt();
            List<Order> filtered = new ArrayList<>();

            switch (choice) {
                case 1 -> filtered = getAll();
                case 2 -> filtered = getByEmail();
                case 3 -> filtered = getByDate();
                case 4 -> goOn = false;
                case 5 -> Exit.exit();
                default -> {
                    PrintHelper.printError("Invalid choice");
                    continue;
                }
            }

            showProduct(filtered);
        }
    }

    private List<Order> getByDate() {
        PrintHelper.ask("Enter start date - new line - end date");
        String startDate = ScannerWrapper.nextLine();
        String endDate = ScannerWrapper.nextLine();
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return adminController.getByDate(start, end);
        } catch (DateTimeParseException e) {
            PrintHelper.printError("Invalid date format. Please use yyyy-MM-dd.");
            return getByDate();
        }
    }

    private List<Order> getByEmail() {
        PrintHelper.ask("Enter email");
        String email = ScannerWrapper.nextLine();
        return adminController.getByEmail(email);
    }

    private List<Order> getAll() {
        return adminController.getAllOrders();
    }

    private static void showProduct(List<Order> filtered) {
        int chosen = SplitDisplay.show(filtered);

        if (chosen > 0 && chosen < filtered.size()) {
            Product product = filtered.get(chosen).getProduct();
            if (product instanceof Mobile) {
                ShowProductInfo.showMobile((Mobile) product);
            } else if (product instanceof Laptop) {
                ShowProductInfo.showLaptop((Laptop) product);
            } else if (product instanceof Book) {
                ShowProductInfo.showBook((Book) product);
            } else {
                PrintHelper.printError("sth went wrong we are very sorry");
            }
        }
    }

    private void showReports() {
        adminController.showReports();
    }

    private void showAuthRequest() {
        int choice = adminController.showAuthREq();
        PrintHelper.option(1, "answer");
        PrintHelper.option(2, "ignore and return");
        int chosen = ScannerWrapper.nextInt();

        switch (chosen) {
            case 1 -> answer(choice);
            case 2 -> {
            }
            default -> PrintHelper.printError("Invalid command");
        }
    }

    private void answer(int choice) {
        adminController.sendAuthRes(choice);
    }
}
