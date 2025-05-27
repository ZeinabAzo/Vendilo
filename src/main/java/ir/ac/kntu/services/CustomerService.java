package ir.ac.kntu.services;

import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.models.Cart;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Order;
import ir.ac.kntu.models.Address;

import static ir.ac.kntu.util.PrintHelper.printError;

public class CustomerService {

    private Customer customer;
    public static double shippingFee = 50;


    public CustomerService(Customer customer) {
        this.customer=customer;
    }

    public boolean purchaseCart(Cart cart, Customer customer, Address address) {
        if (cart == null || customer == null) {
            printError("Cart or customer not recognized.");
            return false;
        }

        double totalPrice = getTotalPrice(cart, address);

        boolean success = customer.getWallet().withdraw(totalPrice);

        if (!success) {
            printError("Insufficient balance in wallet.");
            return false;
        }

        Cart customerCart = customer.getCart(cart);
        if (customerCart == null) {
            printError("The cart was not found in customer's profile.");
            return false;
        }

        customerCart.setPurchased(true);
        return true;
    }

    private static double getTotalPrice(Cart cart, Address address) {
        boolean shippingCost = true;
        double totalPrice = 0;

        for (Order order : cart.getOrders()) {
            if (order != null && order.getProduct() != null) {
                if (!(order.getSeller().getShopLocation().getState().equals(address.getState()))) {
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
}
