package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.*;
import ir.ac.kntu.services.AdminAuthService;
import ir.ac.kntu.services.CustomerAuthService;
import ir.ac.kntu.services.SellerAuthService;
import ir.ac.kntu.ui.ShowProductInfo;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.SplitDisplay;
import jdk.jfr.Label;

import java.util.ArrayList;
import java.util.Spliterator;

public class SellerController {
    private SellerDB sellerDB;
    private ProductDB productDB;
    private Seller seller;

    public SellerController( SellerDB sellerDB,ProductDB productDB, Seller seller){
        this.seller=seller;
        this.sellerDB=sellerDB;
        this.productDB=productDB;
    }

    public void setServices(){//add necessary services
    }

    public Product showProducts(){
        ArrayList<Product> products = seller.getProductsForSale();
        int chosen = SplitDisplay.show(products);
        if(chosen<-1 || chosen>products.size()){
            PrintHelper.printError("Invalid choice!");
            return null;
        } else if (chosen == -1) {
            return null;
        }
        ShowProductInfo showProductInfo=new ShowProductInfo(productDB);
        if(products.get(chosen) instanceof Mobile){
            showProductInfo.showMobile((Mobile) products.get(chosen));
        }else if(products.get(chosen) instanceof Laptop){
            showProductInfo.showLaptop((Laptop) products.get(chosen));
        }else if (products.get(chosen) instanceof Book){
            showProductInfo.showBook((Book) products.get(chosen));
        }
        return products.get(chosen);
    }

    public boolean changeInventory(int count, Product product){
        return product.setInventory(product.getInventory()+count);
    }

}
