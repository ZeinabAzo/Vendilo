package ir.ac.kntu.data;

import ir.ac.kntu.models.Book;
import ir.ac.kntu.models.Laptop;
import ir.ac.kntu.models.Mobile;
import ir.ac.kntu.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    private List<Product> products;

    public ProductDB() {
        this.products = new ArrayList<>();
    }


    public void addMobile(Mobile mobile) {
        products.add(mobile);
    }

    public void addLaptop(Laptop laptop) {
        products.add(laptop);
    }

    public void addBook(Book book) {
        products.add(book);
    }


    public List<Product> getProducts() {
        return products;
    }
}
