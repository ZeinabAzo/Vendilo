package ir.ac.kntu.models;

import java.util.ArrayList;

public class Customer extends User {

    private String email;
    private String phoneNumber;
    private ArrayList<Address> addresses = new ArrayList<>();
    private ArrayList<Cart> carts=new ArrayList<>();

    public Customer(String fName, String lName, String email,
                    String phoneNumber, String password){
        super(fName, lName, password);
        setWallet(new Wallet(true));
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddress(ArrayList<Address> address) {
        this.addresses = address;
    }


    public void addAddress(Address address) {
        this.addresses.add(address);
    }


    public void removeAddress(Address address) {
        this.addresses.remove(address);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public Cart getCart(int index) {
        return carts.get(index);
    }

    public Cart getCart(Cart cart){
        return carts.stream().filter(c -> c.equals(cart)).findFirst().orElse(null);
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }
}
