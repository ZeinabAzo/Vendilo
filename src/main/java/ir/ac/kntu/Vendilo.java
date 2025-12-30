package ir.ac.kntu;

import ir.ac.kntu.controllers.Navigate;
import ir.ac.kntu.data.*;
import ir.ac.kntu.models.*;
import ir.ac.kntu.ui.EntryMenu;
import ir.ac.kntu.util.*;
import ir.ac.kntu.util.loaddb.*;

import java.util.List;

public class Vendilo {

    public void run() {

        AdminWrapper adminWrapper = AdminDatabase.load(); // load full wrapper
        List<Customer> customers = CustomerDatabase.load();
        List<Seller> sellers = SellerDatabase.load();
        List<Product> products = ProductDatabase.load();
        List<Manager> managers = ManagerDataBase.load();

        CustomerDB customerDB = new CustomerDB(customers);
        SellerDB sellerDB = new SellerDB(sellers);
        AdminDB adminDB = new AdminDB(adminWrapper); // use full data
        ProductDB productDB = new ProductDB();
        ManagerDB managerDB = new ManagerDB(managers);

        addProducts(products, productDB);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            AdminDatabase.save(new AdminWrapper(
                    adminDB.getAdmins(),
                    adminDB.getReports()
            ));
            CustomerDatabase.save(customerDB.getCustomers());
            SellerDatabase.save(sellerDB.getSellers());
            ProductDatabase.save(productDB.getProducts());
            ManagerDataBase.save(managerDB.getManagers());
            PrintHelper.printSuccess("All data saved successfully.");
        }));

        Navigate navigator = new Navigate(customerDB, sellerDB, adminDB, productDB, managerDB);
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