package ir.ac.kntu.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cart {   //contains orders, whether purchased or not

    private List<Order> orders = new ArrayList<>();
    private Address shippingAddress;
    private boolean isPurchased = false;
    private double amountPurchased;
    private LocalDate datePurchased;

    public Cart(){
    }

    public Cart(List<Order> orders) {
        this.orders = orders;
    }

    public double getAmountPurchased() {
        return amountPurchased;
    }

    public void setAmountPurchased(double amountPurchased) {
        this.amountPurchased = amountPurchased;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrders(Order order){
        orders.add(order);
    }

    public void addToCart(Order order) {
        orders.add(order);
    }

    public void removeFromCart(Order order) {
        orders.remove(order);
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Cart:\n");
        builder.append("Purchased: ").append(isPurchased ? "Yes" : "No").append("\n");

        if (shippingAddress != null) {
            builder.append("Shipping Address: ").append(shippingAddress).append("\n");
        } else {
            builder.append("Shipping Address: Not set\n");
        }

        builder.append("Orders:\n");
        if (orders.isEmpty()) {
            builder.append("  (No orders in the cart)\n");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                builder.append("  ").append(i + 1).append(". ").append(orders.get(i)).append("\n");
            }
        }

        return builder.toString();
    }

}
