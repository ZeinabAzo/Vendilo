package ir.ac.kntu.services;

import ir.ac.kntu.data.SellerDB;

import java.util.Random;

public class GenerateShopID {

    private SellerDB sellerDB;

    public GenerateShopID(SellerDB sellerDB) {
        this.sellerDB = sellerDB;
    }

    public String generateID() {
        Random random = new Random();
        String shopID = "";

        char char1 = (char) (random.nextInt(26) + 'A');
        char char2 = (char) (random.nextInt(26) + 'A');

        int randomNum = random.nextInt(10000);// a 4 digit num
        String numStr = String.format("%04d", randomNum);
        shopID = "" + char1 + char2 + numStr;

        if (hasDuplicate(shopID)) {
            return generateID();
        } else {
            return shopID;
        }

    }

    public boolean hasDuplicate(String thisId) {
        return sellerDB.getSellers().stream()
                .anyMatch(s -> s.getShopID().equals(thisId));
    }

}
