package ir.ac.kntu.data;

import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDB {

    private ArrayList<Product> products;

    public ProductDB(ArrayList<Product> products) {
        this.products = products;
    }


    public void addProduct(Product product){
        products.add(product);
    }


    public ArrayList<Product> getProducts() {
        return products;
    }
}
