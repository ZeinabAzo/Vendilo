package ir.ac.kntu.services;

import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Address;
import ir.ac.kntu.models.Seller;

public class SellerAuthService extends AuthService {

    private SellerDB sellerDB;

    public SellerAuthService(SellerDB sellerDB){
        super();
        this.sellerDB=sellerDB;
    }

    public boolean isValidInput(String[] name , String password, String ID, String phoneNumber, String state){
        return isValidName(name[1]) && isValidName(name[2]) && isValidPassword(password) &&
                isValidID(ID) && isValidPhoneNumber(phoneNumber) && isValidSellerAddress(state);
    }

    public Address addressDetector(String state){
        if(isValidSellerAddress(state)){
            return new Address(state);
        }
        return null;
    }

    public Seller login(String shopID, String password){
        return sellerDB.getSellers().stream().filter(s -> s != null && s.getShopID().equals(shopID)
                && s.getPassword().equals(password)).findFirst().orElse(null);
    }

    public Seller signUp(String[] name, String password, String ID, String phoneNumber, String state){
        if(isValidInput( name, password, ID, phoneNumber, state)){
            Address shopLocation = addressDetector(state);
            Seller seller= new Seller(name[1], name[2], password, ID, phoneNumber, shopLocation);
            sellerDB.addSeller(seller);
            return seller;
        }
        return null;
    }
}
