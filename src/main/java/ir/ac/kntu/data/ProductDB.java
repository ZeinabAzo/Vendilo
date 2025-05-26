package ir.ac.kntu.data;

import ir.ac.kntu.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    private List<Product> products;

    public ProductDB(List<Product> products) {
        this.products = new ArrayList<>(products);
    }


    public void addProduct(Product product) {
        products.add(product);
    }


    public List<Product> getProducts() {
        return products;
    }
}
