package ir.ac.kntu.models;

import java.util.ArrayList;

public class Cart {   //contains orders, whether purchased or not

    private ArrayList<Order> orders= new ArrayList<>();
    private Address shippingAddress;
    private boolean isPurchased=false;

    public Cart(ArrayList<Order> orders){
        this.orders=orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addToCart(Order order){
        orders.add(order);
    }

    public void removeFromCart(Order order){
        orders.remove(order);
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }
}
