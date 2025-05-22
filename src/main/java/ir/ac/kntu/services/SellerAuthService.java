package ir.ac.kntu.services;

import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Address;
import ir.ac.kntu.models.Seller;

import java.util.HashMap;

public class SellerAuthService extends AuthService {

    private SellerDB sellerDB;

    public SellerAuthService(SellerDB sellerDB) {
        super();
        this.sellerDB = sellerDB;
    }

    public boolean isValidInput(String firstName, String lastName, String password, String ID, String phoneNumber,
                                String state) {
        return isValidName(firstName) && isValidName(lastName) && isValidPassword(password)
                && isValidID(ID) && isValidPhoneNumber(phoneNumber) && isValidSellerAddress(state);
    }

    public Address addressDetector(String state) {
        if (isValidSellerAddress(state)) {
            return new Address(state);
        }
        return null;
    }

    public Seller login(HashMap<String, String> info) {
        String shopID = info.get("shopID");
        String password = info.get("password");

        if (isValidID(shopID) && isValidPassword(password)) {
            return sellerDB.getSellers().stream()
                    .filter(s -> s != null && s.getShopID().equals(shopID)
                            && s.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public Seller signUp(HashMap<String, String> info) {
        String firstName = info.get("firstname");
        String lastName = info.get("lastname");
        String password = info.get("password");
        String shopID = info.get("shopID");
        String state = info.get("state");
        String phoneNumber = info.get("phone number");

        Address shopLocation= new Address("state");

        if (isValidInput(firstName, lastName, password, shopID, phoneNumber, state)) {
            Seller seller = new Seller(firstName, lastName, shopID, phoneNumber, shopLocation, password);
            sellerDB.addSeller(seller);
            return seller;
        }
        return null;
    }
}
