package ir.ac.kntu;

import ir.ac.kntu.controllers.Navigate;
import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.models.*;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.ui.EntryMenu;
import ir.ac.kntu.util.*;
import ir.ac.kntu.util.loaddb.AdminDatabase;
import ir.ac.kntu.util.loaddb.CustomerDatabase;
import ir.ac.kntu.util.loaddb.ProductDatabase;
import ir.ac.kntu.util.loaddb.SellerDatabase;

import java.util.List;

public class Vendilo {

    public void run() {

        List<Admin> admins = AdminDatabase.load();
        List<Customer> customers = CustomerDatabase.load();
        List<Seller> sellers = SellerDatabase.load();
        List<Product> products = ProductDatabase.load();

        CustomerDB customerDB = new CustomerDB(customers);
        SellerDB sellerDB = new SellerDB(sellers);
        AdminDB adminDB = new AdminDB(admins);
        ProductDB productDB = new ProductDB();

        addProducts(products, productDB);


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            AdminDatabase.save(adminDB.getAdmins());
            CustomerDatabase.save(customerDB.getCustomers());
            SellerDatabase.save(sellerDB.getSellers());
            ProductDatabase.save(productDB.getProducts());
            PrintHelper.printSuccess("All data saved successfully.");
        }));

        Navigate navigator = new Navigate(customerDB, sellerDB, adminDB, productDB);
        navigator.setServices();

        EntryMenu entryMenu = new EntryMenu(navigator);
        entryMenu.entry();
    }

    public static void addProducts(List<Product> products, ProductDB productDB) {
        for(Product p : products){
            if(p instanceof Mobile){
                productDB.addMobile((Mobile) p);
            }else if(p instanceof Book){
                productDB.addBook((Book) p);
            }else if(p instanceof Laptop){
                productDB.addLaptop((Laptop) p);
            }
        }
    }
}