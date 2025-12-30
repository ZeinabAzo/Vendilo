package ir.ac.kntu.ui.adminmenu;

import ir.ac.kntu.controllers.AdmControl;
import ir.ac.kntu.enums.ReportType;
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
            PrintHelper.option(1, "Reports");
            PrintHelper.option(2, "Orders");
            PrintHelper.option(3, "return");
            PrintHelper.lowerBorder("Welcome dear admin");
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> showReports();
                case 2 -> showOrders();
                case 3 -> goON = false;
                default -> PrintHelper.printError("Invalid command");
            }
        }
    }

    private void showOrders() {
        while (true) {
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
                case 4 -> {
                    return;
                }
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
        List<ReportType> reportTypes = adminController.getAdmin().getAccesses();
        if (reportTypes == null || reportTypes.isEmpty()) {
            PrintHelper.printInfo("You don't have access to any of the report types.");
            return;
        }

        PrintHelper.printInfo("Available report types:");
        for (int i = 0; i < reportTypes.size(); i++) {
            PrintHelper.option(i + 1, formatEnumName(reportTypes.get(i)));
        }

        PrintHelper.ask("Choose a report type (enter number):");
        int choice = ScannerWrapper.nextInt() - 1;

        if (choice >= 0 && choice < reportTypes.size()) {
            ReportType selected = reportTypes.get(choice);
            PrintHelper.printInfo("You selected: " + selected);
            decideForType(selected);
        } else {
            PrintHelper.printInfo("Invalid choice. Try again.");
        }
    }

    private void decideForType(ReportType selected) {
        List<Report> reports = adminController.getReports(selected);
        if (reports == null || reports.isEmpty()) {
            PrintHelper.printInfo("No reports found for this type.");
            return;
        }

        int choice = SplitDisplay.show(reports);
        if (choice < 0 || choice >= reports.size()) {
            PrintHelper.printInfo("Invalid choice.");
            return;
        }

        Report report = reports.get(choice);

        if (report.getReportType() == ReportType.AUTHENTICATION) {
            authRequest((AuthRequest) report);

        } else {
            PrintHelper.ask("Set a response:");
            String answer = ScannerWrapper.nextLine();
            report.setResponse(answer);
            report.setResponseStatus(true);
        }
    }

    private static void authRequest(AuthRequest report) {
        PrintHelper.ask("Do you want to accept this seller's request? (yes/no with reason)");
        String answer = ScannerWrapper.nextLine();
        AuthRequest authRequest = report;

        if ("yes".equalsIgnoreCase(answer.trim())) {
            authRequest.setAccepted(true);
        } else {
            authRequest.setAccepted(false);
            authRequest.setResponse(answer);
        }
        authRequest.setResponseStatus(true);
    }


    private String formatEnumName(ReportType type) {
        return type.name().toLowerCase().replace('_', ' ');
    }

}
