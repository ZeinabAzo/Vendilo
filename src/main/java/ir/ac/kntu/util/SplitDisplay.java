package ir.ac.kntu.util;

import ir.ac.kntu.models.*;

import java.util.ArrayList;
import java.util.List;

public class SplitDisplay {

    public static int show(List<?> list) {
        if (list == null || list.isEmpty()) {
            PrintHelper.printError("Nothing to display.");
            return -1;
        }

        int page = 0;
        int pageSize = 10;
        int totalPages = (int) Math.ceil(list.size() / (double) pageSize);

        while (true) {
            String choice = getStringChoice(list, page, pageSize, totalPages);

            if (isNumber(choice)) {
                Integer selected = getSelected(list, choice);
                if (selected != null) {
                    return selected;
                }
            } else if (handlePageChoice(choice)) {
                page = updatePage(choice, page, totalPages);
            } else if ("r".equalsIgnoreCase(choice)) {
                return -1;
            } else if ("e".equalsIgnoreCase(choice)) {
                Exit.exit();
            } else {
                PrintHelper.printError("Invalid input. Try again.");
            }
        }
    }

    private static boolean isNumber(String input) {
        return input.matches("\\d+");
    }

    private static boolean handlePageChoice(String input) {
        return "n".equalsIgnoreCase(input) || "p".equalsIgnoreCase(input);
    }

    private static int updatePage(String choice, int page, int totalPages) {
        return switch (choice.toLowerCase()) {
            case "n" -> getPageCase1(page, totalPages);
            case "p" -> getPageCase2(page);
            default -> page;
        };
    }


    private static String getStringChoice(List<?> list, int page, int pageSize, int totalPages) {
        List<?> tempList = getObjects(list, page, pageSize);
        int currentState = getCurrentState(totalPages, page);
        fancyPrint(tempList, currentState, page + 1, totalPages);

        PrintHelper.ask("Enter choice (number to select, 'n' for next, 'p' for previous, 'r' to return, " +
                "'e' to exit):");
        return ScannerWrapper.nextLine().trim();
    }

    private static List<?> getObjects(List<?> list, int page, int pageSize) {
        int start = page * pageSize;
        int end = Math.min(start + pageSize, list.size());
        return new ArrayList<>(list.subList(start, end));
    }

    private static int getPageCase1(int page, int totalPages) {
        if (page < totalPages - 1) {
            page++;
        } else {
            PrintHelper.printInfo("You are on the last page.");
        }
        return page;
    }

    private static int getPageCase2(int page) {
        if (page > 0) {
            page--;
        } else {
            PrintHelper.printInfo("You are on the first page.");
        }
        return page;
    }

    private static Integer getSelected(List<?> list, String choice) {
        int selected = Integer.parseInt(choice);
        if (selected >= 0 && selected < list.size()) {
            return selected;
        } else {
            PrintHelper.printError("Invalid index. Try again.");
        }
        return null;
    }

    private static int getCurrentState(int totalPages, int page) {
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
        return currentState;
    }

    private static void fancyPrint(List<?> tempList, int currentState, int currentPage, int totalPages) {
        PrintHelper.upperBorder("Page " + currentPage + " of " + totalPages);

        for (int index = 0; index < tempList.size(); index++) {
            Object item = tempList.get(index);
            printIt(item, index);
        }
        PrintHelper.newLine();

        switch (currentState) {
            case 0 -> PrintHelper.printInfo("Options: [n] Next | [r] Return | [e] Exit");
            case 1 -> PrintHelper.printInfo("Options: [p] Previous | [n] Next | [r] Return | [e] Exit");
            case 2 -> PrintHelper.printInfo("Options: [p] Previous | [r] Return | [e] Exit");
            case 3 -> PrintHelper.printInfo("Options: [r] Return | [e] Exit");
            default -> {
                PrintHelper.printError("Invalid command");
                return;
            }
        }

        PrintHelper.lowerBorder("Navigation");
    }

    private static void printIt(Object item, int index) {
        if (item instanceof Product) {
            List<Product> product = new ArrayList<>();
            product.add((Product) item);
            PrintHelper.option(index);
            ProductPrinter.printPretty(product);
        } else if (item instanceof Order order) {
            System.out.println(order);
        } else if (item instanceof Cart cart) {
            System.out.println(cart);
        } else if (item instanceof Address address) {
            System.out.println(address);
        } else if (item instanceof Transaction transaction) {
            System.out.println(transaction);
        } else if (item instanceof AuthRequest authRequest) {
            System.out.println(authRequest);
        }
    }
}
