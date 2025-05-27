package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.models.*;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

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

    private void searchByName() {
        PrintHelper.ask("Enter the product name:");
        String name = ScannerWrapper.nextLine();
        List<Product> filtered =  cusControl.searchByName(name);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }

        showProducts(filtered);
    }

    private void searchByType() {
        PrintHelper.ask("Enter the product type:");
        String type = ScannerWrapper.nextLine();
        List<Product> filtered = cusControl.searchByType(type);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }

        showProducts(filtered);
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

        List<Product> filtered =  cusControl.searchByNameAndPrice(name, range);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }

        showProducts(filtered);
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

        showProducts(filtered);
    }

    private void allFiltered() {
        PrintHelper.ask("Enter the product name:");
        String name = ScannerWrapper.nextLine();

        PrintHelper.ask("Enter the product type:");
        String type = ScannerWrapper.nextLine();

        double[] range = askPriceRange();

        List<Product> filtered =  cusControl.searchByAllFilters(type, name, range);

        if (filtered == null || filtered.isEmpty()) {
            PrintHelper.printError("No products found.");
            return;
        }

        showProducts(filtered);
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
        if(product instanceof  Mobile){
            ShowProductInfo.showMobile((Mobile) product);
        }else if(product instanceof  Laptop){
            ShowProductInfo.showLaptop((Laptop) product);
        }else if(product instanceof Book){
            ShowProductInfo.showBook((Book) product);
        }else {
            PrintHelper.printError("couldn't find class in showProduct of cusSearchMenu");
        }
    }
}
