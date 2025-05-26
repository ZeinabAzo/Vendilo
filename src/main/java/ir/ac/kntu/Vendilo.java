package ir.ac.kntu;

import ir.ac.kntu.controllers.Navigate;
import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.ui.EntryMenu;
import ir.ac.kntu.util.*;

import java.util.ArrayList;
import java.util.List;

public class Vendilo {

    public static final String ADMIN_JSON_PATH = "database/admins.json";
    public static final String CUSTOMER_JSON_PATH = "database/customers.json";
    public static final String SELLER_JSON_PATH = "database/sellers.json";
    public static final String PRODUCT_JSON_PATH = "database/products.json";


    public void run() {

        List<Admin> admins = AdminDatabase.load();
        List<Customer> customers = CustomerDatabase.load();
        List<Seller> sellers = SellerDatabase.load();
        List<Product> products = ProductDatabase.load();

        CustomerDB customerDB = new CustomerDB(customers);
        SellerDB sellerDB = new SellerDB(sellers);
        AdminDB adminDB = new AdminDB(admins);
        ProductDB productDB = new ProductDB(products);

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
}