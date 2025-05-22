package ir.ac.kntu;

import ir.ac.kntu.controllers.Navigate;
import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;

public class Vendilo{

    public void run(){
        CustomerDB customerDB=new CustomerDB();
        SellerDB sellerDB=new SellerDB();
        AdminDB adminDB=new AdminDB();
        ProductDB productDB=new ProductDB();

        Navigate navigator = new Navigate(customerDB, sellerDB, adminDB, productDB);
        navigator.setServices();
    }
}
