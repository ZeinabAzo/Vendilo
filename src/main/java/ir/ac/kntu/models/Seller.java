package ir.ac.kntu.models;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {

    private String nationalId;
    private String phoneNumber;
    private Address shopLocation;
    private String shopID;
    private List<Product> productsForSale;
    private List<Order> orders;
    //after the seller gets verified by an admin, we generate a shopID for him/her
    //then the seller can access menu and things related to it.

    public Seller(String fName, String lName, String nationalId, String phoneNumber,
                  Address shopLocation, String password) {
        super(fName, lName, password);
        setWallet(new Wallet(false));
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        this.shopLocation = shopLocation;
        this.shopID = null;//generate later
        productsForSale = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public List<Product> getProductsForSale() {
        return productsForSale;
    }

    public void addMobile(Mobile mobile) {
        productsForSale.add(mobile);
    }

    public void addLaptop(Laptop laptop) {
        productsForSale.add(laptop);
    }

    public void addBook(Book book) {
        productsForSale.add(book);
    }

    public Address getShopLocation() {
        return shopLocation;
    }

    public String getShopID() {
        return shopID;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "shopID='" + shopID + '\'' +
                ", shopLocation=" + shopLocation +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
