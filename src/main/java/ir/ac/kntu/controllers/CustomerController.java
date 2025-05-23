package ir.ac.kntu.controllers;

import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.models.*;
import ir.ac.kntu.services.SearchProducts;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.SplitDisplay;

import java.time.LocalDate;
import java.util.HashMap;

public class CustomerController {

    private ProductDB productDB;
    private Customer customer;
    private SearchProducts searchProducts;

    public CustomerController(Customer customer, ProductDB productDB) {
        this.customer = customer;
        this.productDB = productDB;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setServices() {//add necessary services
        SearchProducts searchProducts = new SearchProducts(productDB);
    }

    public HashMap<Seller, Product> searchByName(String name) {
        return searchProducts.searchProductByName(name);
    }

    public void orderProduct(Product product) {
        Cart cart= findCart();
        Order order=new Order(product, customer, product.getSeller(), LocalDate.now());
        cart.addToCart(order);
    }

    private Cart findCart() {
        int chosen = SplitDisplay.show(customer.getCarts());
        return customer.getCart(chosen);
    }

    public HashMap<Seller, Product> searchByType(String type) {
        try {
            Class<?> className = Class.forName("ir.ac.kntu.models." + type);
            return searchProducts.searchByType(className);
        } catch (ClassNotFoundException e) {
            PrintHelper.printError("Invalid type: " + type);
            return null;
        }
    }
}