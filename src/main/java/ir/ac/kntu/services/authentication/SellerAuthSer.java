package ir.ac.kntu.services.authentication;

import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Address;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.services.GenerateShopID;
import ir.ac.kntu.util.PrintHelper;

import java.util.Map;

public class SellerAuthSer extends AuthService {

    private SellerDB sellerDB;

    public SellerAuthSer(SellerDB sellerDB) {
        super();
        this.sellerDB = sellerDB;
    }

    public boolean isValidInput(String[] name, String[] stuff, String state) {
        return isValidName(name) && isValidPassword(stuff[0])
                && isValidID(stuff[1]) && isValidPhoneNumber(stuff[2])
                && isValidSellerAddress(state) && isUnique(stuff[0], stuff[1], stuff[2]);
    }

    private boolean isUnique(String password, String thisID, String phoneNum) {
        Seller seller = sellerDB.getSellers().stream().filter(c -> password.equals(c.getPassword())
                || thisID.equals(c.getNationalId()) || phoneNum.equals(c.getPhoneNumber())).findFirst().orElse(null);
        if (seller == null) {
            return true;
        } else {
            PrintHelper.printError("Found duplicates for your email, phone number or password");
            return false;
        }
    }

    public Address addressDetector(String state) {
        if (isValidSellerAddress(state)) {
            return new Address(state);
        }
        return null;
    }

    public Seller login(Map<String, String> info) {
        String shopID = info.get("shopID");
        String password = info.get("password");

        return sellerDB.getSellers().stream()
                .filter(s -> s != null && s.getShopID().equals(shopID)
                        && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);

    }

    public Seller signUp(Map<String, String> info) {
        String firstName = info.get("firstname");
        String lastName = info.get("lastname");
        String password = info.get("password");
        String nationalId = info.get("ID");
        String state = info.get("state");
        String phoneNumber = info.get("phone number");

        Address shopLocation = addressDetector(state);

        if (isValidInput(new String[]{firstName, lastName}, new String[]{password, nationalId, phoneNumber}, state)) {
            Seller seller = new Seller(firstName, lastName, nationalId, phoneNumber, shopLocation, password);
            GenerateShopID generateShopID = new GenerateShopID(sellerDB);
            String thisId = generateShopID.generateID();
            seller.setShopID(thisId);
            sellerDB.addSeller(seller);
            return seller;
        }
        return null;
    }
}
