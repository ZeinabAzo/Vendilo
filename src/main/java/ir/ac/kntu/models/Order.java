package ir.ac.kntu.models;

import java.time.LocalDate;

public class Order {

    private Product product;
    private Customer customer;
    private Seller seller;
    private LocalDate orderDate;
    private Address startLocation;   // Seller’s shop
    private Address shippingAddress; // Customer’s delivery address

    public Order(Product product, Customer customer, Seller seller, LocalDate orderDate,
                 Address shippingAddress) {
        this.product = product;
        this.customer = customer;
        this.seller=seller;
        this.orderDate = orderDate;
        startLocation = seller.getAddress();
        this.shippingAddress=shippingAddress;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        this.startLocation = seller.getAddress(); // Ensure consistency
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Address getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Address startLocation) {
        this.startLocation = startLocation;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}
