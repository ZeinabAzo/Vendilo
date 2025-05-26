package ir.ac.kntu.models;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {

    private String nationalId;
    private String phoneNumber;
    private Address shopLocation;
    private String shopID;
    private List<Product> productsForSale;

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
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public List<Product> getProductsForSale() {
        return productsForSale;
    }

    public void addProduct(Product product) {
        productsForSale.add(product);
    }

    public Address getShopLocation() {
        return shopLocation;
    }

    public String getShopID() {
        return shopID;
    }

}
