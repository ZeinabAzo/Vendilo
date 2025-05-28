package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.models.User;
import ir.ac.kntu.services.AdminAuthSer;
import ir.ac.kntu.services.CustAuthSer;
import ir.ac.kntu.services.SellerAuthSer;
import ir.ac.kntu.ui.CusMainMenu;
import ir.ac.kntu.ui.SellerMainMenu;
import ir.ac.kntu.util.PrintHelper;

import java.util.Map;

public class Navigate {

    private CustomerDB customerDB;
    private SellerDB sellerDB;
    private AdminDB adminDB;
    private ProductDB productDB;
    private AdminAuthSer adminAuthSer;
    private CustAuthSer custAuthSer;
    private SellerAuthSer sellerAuthSer;

    public Navigate(CustomerDB customerDB, SellerDB sellerDB, AdminDB adminDB, ProductDB productDB) {
        this.customerDB = customerDB;
        this.sellerDB = sellerDB;
        this.adminDB = adminDB;
        this.productDB = productDB;
    }

    public void setServices() {//add necessary services
        adminAuthSer = new AdminAuthSer(adminDB);
        custAuthSer = new CustAuthSer(customerDB);
        sellerAuthSer = new SellerAuthSer(sellerDB);
    }

    public User leadToSignUP(Map<String, String> info, String user) {
        switch (user.toLowerCase()) {
            case "seller" -> {
                Seller seller = sellerAuthSer.signUp(info);
                String shopId = seller.getShopID();
                PrintHelper.printInfo(" Your shop-ID is : " + shopId + ". ");
                return seller;
            }
            case "customer" -> {
                return custAuthSer.signUp(info);
            }
            default -> {
                return null;
            }
        }
    }

    public User login(String user, Map<String, String> info, String chosenPath) {
        switch (user) {
            case "customer" -> {
                if (chosenPath.equals("email")) {
                    return custAuthSer.loginByEmail(info);
                } else if (chosenPath.equals("phoneNumber")) {
                    return custAuthSer.loginByPhoneNumber(info);
                }
            }
            case "seller" -> {
                return sellerAuthSer.login(info);
            }
            case "admin" -> {
                return adminAuthSer.login(info);
            }
            default -> {
                return null;
            }
        }
        return null;
    }

    public void decideForUser(User user) {

        if (user instanceof Customer) {
            CusControl cusControl = new CusControl((Customer) user,customerDB, sellerDB, adminDB, productDB);
            cusControl.setServices();
            CusMainMenu customerMainMenu = new CusMainMenu(cusControl);
            customerMainMenu.showPage();
        } else if (user instanceof Seller) {
            SellControl sellControl = new SellControl(productDB, (Seller) user);
            sellControl.setServices();
            SellerMainMenu sellerMainMenu= new SellerMainMenu(sellControl);
            sellerMainMenu.showPage();
        } else {
            AdmControl admControl = new AdmControl(adminDB, productDB, sellerDB, (Admin) user);
        }
    }

}
