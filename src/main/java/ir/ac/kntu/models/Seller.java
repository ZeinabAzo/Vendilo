package ir.ac.kntu.models;

import java.util.ArrayList;

public class Seller extends User{

    private String ID;
    private String phoneNumber;
    private Address shopLocation;
    private String shopID;
    private ArrayList<Product> productsForSale;

    //after the seller gets verified by an admin, we generate a shopID for him/her
    //then the seller can access menu and things related to it.

    public Seller(String fName, String lName, String ID, String phoneNumber,
                  Address shopLocation, String password) {
        super(fName, lName, password);
        setWallet(new Wallet(false));
        this.ID = ID;
        this.phoneNumber = phoneNumber;
        this.shopLocation = shopLocation;
        this.shopID = null;//generate later
        productsForSale=new ArrayList<>();
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public ArrayList<Product> getProductsForSale() {
        return productsForSale;
    }

    public Address getShopLocation() {
        return shopLocation;
    }

    public String getShopID() {
        return shopID;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
}
