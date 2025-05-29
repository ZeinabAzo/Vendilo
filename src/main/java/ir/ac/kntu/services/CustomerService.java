package ir.ac.kntu.services;

import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.*;
import ir.ac.kntu.util.PrintHelper;

import static ir.ac.kntu.util.PrintHelper.printError;

public class CustomerService {

    private SellerDB sellerDB;
    public static double shippingFee = 50;
    private CustomerDB customerDB;


    public CustomerService(CustomerDB customerDB, SellerDB sellerDB) {
        this.customerDB = customerDB;
        this.sellerDB = sellerDB;
    }

    public void purchaseCart(Cart cart, Customer customer, Address address) {
        if (cart == null || customer == null) {
            printError("Cart or customer not recognized.");
            return;
        }

        double totalPrice = getTotalPrice(cart, address);

        boolean success = customer.getWallet().withdraw(totalPrice);

        if (!success) {
            printError("Insufficient balance in wallet.");
            return;
        }

        Cart customerCart = customer.getCart(cart);
        if (customerCart == null) {
            printError("The cart was not found in customer's profile.");
            return;
        }

        for(Order order: cart.getOrders()){
            Seller seller= sellerDB.findSeller(order.getShopID());
            seller.getWallet().deposit(order.getProduct().getPrice());
        }

        customerCart.setPurchased(true);
    }

    private double getTotalPrice(Cart cart, Address address) {
        boolean shippingCost = true;
        double totalPrice = 0;

        for (Order order : cart.getOrders()) {
            if (order != null && order.getProduct() != null) {
                Seller seller = sellerDB.findSeller(order.getShopID());
                if (!(seller.getShopLocation().getState().equals(address.getState()))) {
                    shippingCost = false;
                }
                totalPrice += order.getProduct().getPrice();
            }
        }

        if (shippingCost) {
            totalPrice += shippingFee / 3;
        } else {
            totalPrice += shippingFee;
        }
        return totalPrice;
    }

    public void setEmail(Customer customer, String email) {
        if(isCorrectEmail(email)){
            customer.setEmail(email);
        }
    }

    private boolean isCorrectEmail(String email){
        if(AuthService.isValidEmail(email)){
            if (hasDupEmail(email)){
                PrintHelper.printError("Such an evil, using someone else's email!!");
                return false;
            }
            return true;
        }else{
            PrintHelper.printError("Invalid email");
            return false;
        }
    }

    private boolean hasDupEmail(String email){
        String foundEmail = customerDB.getCustomers().stream()
                .map(Customer::getEmail)
                .filter(email::equals)
                .findFirst()
                .orElse(null);
        return foundEmail != null;
    }

    public void setPassword(Customer customer, String password) {
        if (isCorrectPassword(password)) {
            customer.setPassword(password);
        }
    }

    private boolean isCorrectPassword(String password) {
        if (AuthService.isValidPassword(password)) {
            if (hasDupPassword(password)) {
                PrintHelper.printError("Password already in use by another user! Be original!");
                return false;
            }
            return true;
        } else {
            PrintHelper.printError("Invalid password");
            return false;
        }
    }

    private boolean hasDupPassword(String password) {
        String foundPassword = customerDB.getCustomers().stream()
                .map(Customer::getPassword)
                .filter(password::equals)
                .findFirst()
                .orElse(null);
        return foundPassword != null;
    }

    public void deleteUnavailable(Cart cart) {
        cart.getOrders().removeIf(o -> o.getProduct().getInventory() <= 0);
    }
}
