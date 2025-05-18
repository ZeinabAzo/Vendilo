package ir.ac.kntu.models;

public class Seller extends User{

    private String ID;
    private String phoneNumber;
    private Address shopLocation;
    private String shopID;
    private Address address;

    //after the seller gets verified by an admin, we generate a shopID for him/her
    //afterward the seller can access menu and things related to it.

    public Seller(String fName, String lName, String password, String ID, String phoneNumber,
                  Address shopLocation, Address address) {
        super(fName, lName, password);
        setWallet(new Wallet(false)); // calls User constructor
        this.ID = ID;
        this.phoneNumber = phoneNumber;
        this.shopLocation = shopLocation;
        this.shopID = null;
        this.address=address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
}
