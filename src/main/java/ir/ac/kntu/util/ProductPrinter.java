package ir.ac.kntu.util;

import ir.ac.kntu.models.Book;
import ir.ac.kntu.models.Laptop;
import ir.ac.kntu.models.Mobile;
import ir.ac.kntu.models.Product;

import java.util.List;


public class ProductPrinter {

    public static void printPretty(List<? extends Product> products) {
        if (products == null || products.isEmpty()) {
            PrintHelper.printError("No products to display.");
            return;
        }

        String borderColor = PrintHelper.ConsoleColors.BRIGHT_BLUE;
        String textColor = PrintHelper.ConsoleColors.WHITE;
        String reset = PrintHelper.ConsoleColors.RESET;

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            String[] lines = getStrings(product);
            int maxWidth = getMaxLineWidth(lines);

            String top = borderColor + "╔" + "═".repeat(maxWidth + 2) + "╗" + reset;
            String bottom = borderColor + "╚" + "═".repeat(maxWidth + 2) + "╝" + reset;

            String background = i % 2 == 0 ? "" : PrintHelper.ConsoleColors.BG_LIGHT_GRAY;

            System.out.println(top);
            for (String line : lines) {
                String centered = centerText(line, maxWidth);
                System.out.println(borderColor + "║ " + background + textColor + centered + reset + borderColor + " ║" + reset);
            }
            System.out.println(bottom);
        }
    }

    private static String[] getStrings(Product product) {
        String[] lines = new String[0];

        if(product instanceof Mobile) {
            lines = ((Mobile) product).mobileToString().split(" \\| ");
        } else if(product instanceof Laptop){
            lines = ((Laptop) product).lapToString().split(" \\| ");
        } else if(product instanceof Book){
            lines = ((Book) product).bookToString().split(" \\| ");
        } else {
            lines = new String[0];
        }

        return lines;
    }

    private static int getMaxLineWidth(String[] lines) {
        int max = 0;
        for (String line : lines) {
            max = Math.max(max, line.length());
        }
        return max;
    }

    static String centerText(String text, int width) {
        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;
        return " ".repeat(left) + text + " ".repeat(right);
    }
}
