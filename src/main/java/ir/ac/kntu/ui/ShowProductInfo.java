package ir.ac.kntu.ui;

import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.models.Book;
import ir.ac.kntu.models.Laptop;
import ir.ac.kntu.models.Mobile;
import ir.ac.kntu.util.PrintHelper;
import java.util.HashMap;

public class ShowProductInfo {

    private ProductDB productDB;

    public ShowProductInfo(ProductDB productDB){
        this.productDB=productDB;
    }

    public void showMobile(Mobile mobile){

        HashMap<String, String> info=new HashMap<>();
        info.put("name", mobile.getName());
        info.put("price", String.valueOf(mobile.getPrice()));
        info.put("inventory", String.valueOf(mobile.getInventory()));
        info.put("seller", mobile.getSeller().getfName() + " " + mobile.getSeller().getlName());
        info.put("brand", mobile.getBrand());
        info.put("internal storage", String.valueOf(mobile.getInternalStorage()));
        info.put("ram", mobile.getRam());
        info.put("front camera resolution", String.valueOf(mobile.getFrontCameraResolution()));
        info.put("back camera resolution", String.valueOf(mobile.getBackCameraResolution()));
        info.put("mobile network type", String.valueOf(mobile.getMobileNetworkType()));

        PrintHelper.printFancyTable(info);
    }

    public void showLaptop(Laptop laptop){

        HashMap<String, String> info=new HashMap<>();
        info.put("name", laptop.getName());
        info.put("price", String.valueOf(laptop.getPrice()));
        info.put("inventory", String.valueOf(laptop.getInventory()));
        info.put("seller", laptop.getSeller().getfName() + " " + laptop.getSeller().getlName());
        info.put("brand", laptop.getBrand());
        info.put("internal storage", String.valueOf(laptop.getInternalStorage()));
        info.put("ram", laptop.getRam());
        info.put("GPU model", String.valueOf(laptop.getGPUModel()));
        info.put("bluetooth support ", String.valueOf(laptop.hasBtSupport()));
        info.put("webcam status", String.valueOf(laptop.getWebcamStatus()));

        PrintHelper.printFancyTable(info);
    }

    public void showBook(Book book){

        HashMap<String, String> info=new HashMap<>();
        info.put("name", book.getName());
        info.put("price", String.valueOf(book.getPrice()));
        info.put("inventory", String.valueOf(book.getInventory()));
        info.put("seller", book.getSeller().getfName() + " " + book.getSeller().getlName());
        info.put("author name", book.getAuthorName());
        info.put("page count",String.valueOf(book.getPageCount()));
        info.put("book genre", String.valueOf(book.getPageCount()));
        info.put("age group", String.valueOf(book.getBookGenre()));
        info.put("ISBN", book.getISBN());

        PrintHelper.printFancyTable(info);
    }
}
