package ir.ac.kntu.controllers;

import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.*;
import ir.ac.kntu.services.CustomerService;
import ir.ac.kntu.services.SearchProducts;
import ir.ac.kntu.ui.ShowProductInfo;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.SplitDisplay;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CusControl {

    private ProductDB productDB;
    private SellerDB sellerDB;
    private Customer customer;
    private SearchProducts searchProducts;
    private CustomerService customerServ;

    public CusControl(Customer customer, SellerDB sellerDB, ProductDB productDB) {
        this.customer = customer;
        this.productDB = productDB;
        this.sellerDB = sellerDB;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setServices() {//add necessary services
        this.searchProducts = new SearchProducts(productDB);
        this.customerServ = new CustomerService(customer);
    }

    public List<Product> searchByName(String name) {
        return searchProducts.searchProductByName(name);
    }

    public List<Product> searchByNameAndPrice(String name, double[] priceRange) {
        return searchProducts.searchByNameAndPrice(name, priceRange);
    }

    public List<Product> searchByTypeAndPrice(String type, double[] priceRange) {
        return searchProducts.searchByTypeAndPrice(type, priceRange);

    }

    public List<Product> searchByAllFilters(String type, String name, double[] priceRange) {
        return searchProducts.allFilteredSearch(priceRange, name, type);

    }

    public void orderProduct(Product product) {
        Cart cart = findCart();
        Seller seller = sellerDB.findSeller(product.getSellerId());
        Order order = new Order(product, seller, LocalDate.now());
        cart.addToCart(order);
    }

    private Cart findCart() {
        int chosen = SplitDisplay.show(customer.getCarts());
        if (chosen == -1) {
            PrintHelper.printInfo("Darling you've got no cart, let's create one:");
            Cart cart = new Cart();
            customer.setCarts(cart);
            PrintHelper.printSuccess("We have created a new cart just for you. ♡❤ ");
            return cart;
        }
        return customer.getCart(chosen);
    }

    public void deleteCart(Cart c) {
        Cart cart = customer.getCart(c);
        if (cart != null) {
            customer.getCarts().remove(c);
        } else {
            PrintHelper.printError("cart is null: cusControl-deleteCart");
        }
    }

    public List<Product> searchByType(String type) {
        return searchProducts.searchByType(type);
    }

    public void showProductInfo(Product product) {
        // TODO
    }

    public void purchaseCart(Address address, Cart cart) {
        if (!customer.getCart(cart).isPurchased()) {
            customerServ.purchaseCart(cart, customer, address);
        } else {
            PrintHelper.printError("this cart is already purchased sorry");
            return;
        }
    }
}