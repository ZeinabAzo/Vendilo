package ir.ac.kntu.util;

import ir.ac.kntu.models.Cart;
import ir.ac.kntu.models.Order;
import ir.ac.kntu.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SplitDisplay {

    public static int show(List<?> list) {
        if (list == null || list.isEmpty()) {
            PrintHelper.printError("Nothing to display.");
            return -1;
        }

        final int pageSize = 10;
        int page = 0;
        int totalPages = (int) Math.ceil(list.size() / (double) pageSize);

        while (true) {
            int start = page * pageSize;
            int end = Math.min(start + pageSize, list.size());
            List<?> tempList = new ArrayList<>(list.subList(start, end));

            int currentState;
            if (totalPages == 1) {
                currentState = 3; // only one page
            } else if (page == 0) {
                currentState = 0; // first page
            } else if (page == totalPages - 1) {
                currentState = 2; // last page
            } else {
                currentState = 1; // middle page
            }

            fancyPrint(tempList, currentState, page + 1, totalPages);

            PrintHelper.ask("Enter choice (number to select, 'n' for next, 'p' for previous, 'r' to return, " +
                    "'e' to exit):");
            String choice = ScannerWrapper.nextLine().trim();

            if (choice.matches("\\d+")) {
                int selected = Integer.parseInt(choice);
                if (selected >= 0 && selected < list.size()) {
                    return selected;
                } else {
                    PrintHelper.printError("Invalid index. Try again.");
                }
            } else {
                switch (choice.toLowerCase()) {
                    case "n":
                        if (page < totalPages - 1) {
                            page++;
                        } else {
                            PrintHelper.printInfo("You are on the last page.");
                        }
                        break;
                    case "p":
                        if (page > 0) {
                            page--;
                        } else {
                            PrintHelper.printInfo("You are on the first page.");
                        }
                        break;
                    case "r":
                        return -1;
                    case "e":
                        Exit.exit();
                        break;
                    default:
                        PrintHelper.printError("Invalid input. Try again.");
                }
            }
        }
    }

    private static void fancyPrint(List<?> tempList, int currentState, int currentPage, int totalPages) {
        PrintHelper.upperBorder("Page " + currentPage + " of " + totalPages);

        int baseIndex = (currentPage - 1) * 10;
        for (int i = 0; i < tempList.size(); i++) {
            Object item = tempList.get(i);
            printIt( item, i);
        }
        PrintHelper.newLine();

        switch (currentState) {
            case 0 -> PrintHelper.printInfo("Options: [n] Next | [r] Return | [e] Exit");
            case 1 -> PrintHelper.printInfo("Options: [p] Previous | [n] Next | [r] Return | [e] Exit");
            case 2 -> PrintHelper.printInfo("Options: [p] Previous | [r] Return | [e] Exit");
            case 3 -> PrintHelper.printInfo("Options: [r] Return | [e] Exit");
        }

        PrintHelper.lowerBorder("Navigation");
    }

    private static void printIt(Object item, int i) {
        if(item instanceof Product){
            List<Product> product = new ArrayList<>();
            product.add((Product) item);
            PrintHelper.option(i);
            ProductPrinter.printPretty(product);
        }else if(item instanceof Order order){
            String massage= order.toString();
            System.out.println(massage);
        }else if(item instanceof Cart cart){
            String massage = cart.toString();
            System.out.println(massage);
        }
    }
}
