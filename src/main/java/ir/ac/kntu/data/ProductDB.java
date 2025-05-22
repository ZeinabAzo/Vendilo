package ir.ac.kntu.data;

import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;
import java.util.HashMap;

public class ProductDB {

    private HashMap<Seller, Product> productDB;

    public ProductDB(){
        this.productDB=new HashMap<>();
    }


    public void addProduct(Seller seller, Product product){
        productDB.put(seller, product);
    }

    public HashMap<Seller, Product> getProductDB() {
        return productDB;
    }

}
