package ir.ac.kntu.ui;

import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.models.Book;
import ir.ac.kntu.models.Laptop;
import ir.ac.kntu.models.Mobile;
import ir.ac.kntu.util.PrintHelper;

import java.util.HashMap;

public class ShowProductInfo {

    public static void showMobile(Mobile mobile) {

        HashMap<String, String> info = new HashMap<>();
        info.put("name", mobile.getName());
        info.put("price", String.valueOf(mobile.getPrice()));
        info.put("inventory", String.valueOf(mobile.getInventory()));
        info.put("seller", mobile.getSellerId());
        info.put("brand", mobile.getBrand());
        info.put("internal storage", String.valueOf(mobile.getInternalStorage()));
        info.put("ram", mobile.getRam());
        info.put("front camera resolution", String.valueOf(mobile.getFrontCameraResolution()));
        info.put("back camera resolution", String.valueOf(mobile.getBackCameraResolution()));
        info.put("mobile network type", String.valueOf(mobile.getMobileNetType()));

        PrintHelper.printFancyTable(info);
    }

    public static void showLaptop(Laptop laptop) {

        HashMap<String, String> info = new HashMap<>();
        info.put("name", laptop.getName());
        info.put("price", String.valueOf(laptop.getPrice()));
        info.put("inventory", String.valueOf(laptop.getInventory()));
        info.put("seller", laptop.getSellerId());
        info.put("brand", laptop.getBrand());
        info.put("internal storage", String.valueOf(laptop.getInternalStorage()));
        info.put("ram", laptop.getRam());
        info.put("GPU model", String.valueOf(laptop.getGpuModel()));
        info.put("bluetooth support ", String.valueOf(laptop.hasBtSupport()));
        info.put("webcam status", String.valueOf(laptop.getWebcamStatus()));

        PrintHelper.printFancyTable(info);
    }

    public static void showBook(Book book) {

        HashMap<String, String> info = new HashMap<>();
        info.put("name", book.getName());
        info.put("price", String.valueOf(book.getPrice()));
        info.put("inventory", String.valueOf(book.getInventory()));
        info.put("seller", book.getSellerId());
        info.put("author name", book.getAuthorName());
        info.put("page count", String.valueOf(book.getPageCount()));
        info.put("book genre", String.valueOf(book.getPageCount()));
        info.put("age group", String.valueOf(book.getBookGenre()));
        info.put("ISBN", book.getIsbn());

        PrintHelper.printFancyTable(info);
    }
}
