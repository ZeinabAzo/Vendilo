package ir.ac.kntu.models;

import java.util.ArrayList;

public class Cart {   //contains orders, whether purchased or not

    private ArrayList<Product> products= new ArrayList<>();
    private boolean isPurchased=false;

    public Cart(ArrayList<Product> products){
        this.products=products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> addToCart(Product product){
        products.add(product);
        return products;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }
}
