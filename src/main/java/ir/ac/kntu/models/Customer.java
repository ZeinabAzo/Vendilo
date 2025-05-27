package ir.ac.kntu.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private String email;
    private String phoneNumber;
    private List<Address> addresses;
    private List<Cart> carts;

    public Customer(String fName, String lName, String email,
                    String phoneNumber, String password) {
        super(fName, lName, password);
        setWallet(new Wallet(true));
        this.email = email;
        this.phoneNumber = phoneNumber;
        addresses=new ArrayList<>();
        carts=new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddress(List<Address> address) {
        this.addresses = address;
    }


    public void addAddress(Address address) {
        this.addresses.add(address);
    }


    public void removeAddress(Address address) {
        this.addresses.remove(address);
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public Cart getCart(int index) {
        return carts.get(index);
    }

    public void setCarts(Cart cart) {
        carts.add(cart);
    }

    public Cart getCart(Cart cart) {
        return carts.stream().filter(c -> c.equals(cart)).findFirst().orElse(null);
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }
}
