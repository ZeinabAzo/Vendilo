package ir.ac.kntu.data;

import ir.ac.kntu.models.Seller;
import java.util.ArrayList;

public class SellerDB {

    private  ArrayList<Seller> sellers;

    public SellerDB() {
        this.sellers = new ArrayList<>();
    }

    public SellerDB(ArrayList<Seller> sellers) {
        this.sellers = new ArrayList<>(sellers);
    }

    public ArrayList<Seller> getSellers() {
        return new ArrayList<>(sellers);
    }

    public void addSeller(Seller seller) {
        if (seller != null) {
            sellers.add(seller);
        }
    }

    public int size() {
        return sellers.size();
    }

    public Seller findByShopID(String shopID) {
        return sellers.stream().filter( s -> s.getShopID().equals(shopID)).findFirst().orElse(null);
    }

    public boolean updateSeller(Seller updated) {
        for (int i = 0; i < sellers.size(); i++) {
            if (sellers.get(i).getShopID().equals(updated.getShopID())) {
                sellers.set(i, updated);
                return true;
            }
        }
        return false;
    }


}
