package ir.ac.kntu.models;

import java.util.ArrayList;

public class Customer extends User {

    private String email;
    private String phoneNumber;
    private ArrayList<Address> address = new ArrayList<>();

    public Customer(String fName, String lName, String password, String email,
                    String phoneNumber){
        super(fName, lName, password);
        setWallet(new Wallet(true));
        this.password = password;
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

    public ArrayList<Address> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<Address> address) {
        this.address = address;
    }


    public void addAddress(Address address) {
        this.address.add(address);
    }


    public void removeAddress(Address address) {
        this.address.remove(address);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
}
