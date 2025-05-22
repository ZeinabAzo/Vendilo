package ir.ac.kntu.models;

public class Seller extends User{

    private String ID;
    private String phoneNumber;
    private Address shopLocation;
    private String shopID;

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
