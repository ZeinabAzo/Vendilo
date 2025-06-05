package ir.ac.kntu.ui.cusmenu;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.models.*;
import ir.ac.kntu.util.ShowProductInfo;
import ir.ac.kntu.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ir.ac.kntu.util.Exit.exit;

public class CusSearchMenu {

    private final CusControl cusControl;

    public CusSearchMenu(CusControl cusControl) {
        this.cusControl = cusControl;
    }

    public void firstPage() {
        while (true) {
            PrintHelper.miniUpperBorder("Set needed filters:");
            PrintHelper.option(1, "Product name");
            PrintHelper.option(2, "Product type");
            PrintHelper.option(3, "Price range");
            PrintHelper.option(4, "Return");
            PrintHelper.option(5, "exit");
            PrintHelper.miniLowerBorder("Set needed filters:");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> searchByName();
                case 2 -> searchByType();
                case 3 -> priceRangeOptions();
                case 4 -> {
                    return;
                }
                case 5 -> exit();
                default -> PrintHelper.printError("Invalid command!");
            }
        }
    }

    private List<Product> sort(List<Product> filtered) {
        PrintHelper.ask("Do you want to sort your output?");
        PrintHelper.option(1, "yup, ascending price");
        PrintHelper.option(2, "yup, descending price");
        PrintHelper.option(3, "no, doesn't matter(I'm rich) $-$");
        int choice = ScannerWrapper.nextInt();

        switch (choice) {
            case 1 -> {
                return ascending(filtered);
            }
            case 2 -> {
                return descending(filtered);
            }
            case 3 -> {
                PrintHelper.printInfo("OK Mr.big pockets");
                return filtered;
            }
            default -> {
                PrintHelper.printError("Invalid command");
                return filtered;
            }
        }
    }

    private List<Product> ascending(List<Product> filtered) {

        List<Product> sorted = new ArrayList<>(filtered);

        Collections.sort(sorted);

        return sorted;
    }

    private List<Product> descending(List<Product> filtered) {

        List<Product> sorted = new ArrayList<>(filtered);

        sorted.sort(Collections.reverseOrder());

        return sorted;
    }

    private void searchByName() {
        PrintHelper.ask("Enter the product name:");
        String name = ScannerWrapper.nextLine();
        List<Product> filtered = cusControl.searchByName(name);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }
        showProducts(sort(filtered));
    }

    private void searchByType() {
        PrintHelper.ask("Enter the product type:");
        String type = ScannerWrapper.nextLine();
        List<Product> filtered = cusControl.searchByType(type);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }

        showProducts(sort(filtered));
    }

    private void priceRangeOptions() {
        while (true) {
            PrintHelper.option(1, "Product name");
            PrintHelper.option(2, "Product type");
            PrintHelper.option(3, "Product name and product type");
            PrintHelper.option(4, "Return");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> namePrice();
                case 2 -> typePrice();
                case 3 -> allFiltered();
                case 4 -> {
                    return; // back to main search menu
                }
                default -> PrintHelper.printError("Invalid command!");
            }
        }
    }

    private void namePrice() {
        PrintHelper.ask("Enter the product name:");
        String name = ScannerWrapper.nextLine();
        double[] range = askPriceRange();

        List<Product> filtered = cusControl.searchByNameAndPrice(name, range);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }

        showProducts(sort(filtered));
    }

    private void typePrice() {
        PrintHelper.ask("Enter the product type:");
        String type = ScannerWrapper.nextLine();
        double[] range = askPriceRange();

        List<Product> filtered = cusControl.searchByTypeAndPrice(type, range);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }

        showProducts(sort(filtered));
    }

    private void allFiltered() {
        PrintHelper.ask("Enter the product name:");
        String name = ScannerWrapper.nextLine();

        PrintHelper.ask("Enter the product type:");
        String type = ScannerWrapper.nextLine();

        double[] range = askPriceRange();

        List<Product> filtered = cusControl.searchByAllFilters(type, name, range);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }

        showProducts(sort(filtered));
    }

    private double[] askPriceRange() {
        PrintHelper.ask("Enter the price minimum:");
        double min = ScannerWrapper.nextDouble();
        PrintHelper.ask("Enter the price maximum:");
        double max = ScannerWrapper.nextDouble();
        return new double[]{min, max};
    }

    private void showProducts(List<Product> productList) {
        while (true) {
            int chosen = SplitDisplay.show(productList);
            if (chosen < -1 || chosen >= productList.size()) {
                PrintHelper.printError("Invalid selection.");
                return;
            } else if (chosen == -1) {
                return;
            }

            Product selected = productList.get(chosen);
            showProduct(selected);

            PrintHelper.option(1, "Add to cart");
            PrintHelper.option(2, "Return");

            int choice = ScannerWrapper.nextInt();
            switch (choice) {
                case 1 -> cusControl.orderProduct(selected);
                case 2 -> {
                    return; // back to menu
                }
                default -> PrintHelper.printError("Invalid command.");
            }
        }
    }

    private void showProduct(Product product) {
        if (product instanceof Mobile) {
            ShowProductInfo.showMobile((Mobile) product);
        } else if (product instanceof Laptop) {
            ShowProductInfo.showLaptop((Laptop) product);
        } else if (product instanceof Book) {
            ShowProductInfo.showBook((Book) product);
        } else {
            PrintHelper.printError("couldn't find class in showProduct of cusSearchMenu");
        }
    }

    public static class AddressCus {

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
                    case 4 -> exit();
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
}
