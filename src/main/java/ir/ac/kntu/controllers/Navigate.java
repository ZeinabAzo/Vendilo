package ir.ac.kntu.controllers;

import ir.ac.kntu.data.*;
import ir.ac.kntu.models.*;
import ir.ac.kntu.services.authentication.AdminAuthSer;
import ir.ac.kntu.services.authentication.CustAuthSer;
import ir.ac.kntu.services.authentication.ManagerAuthSer;
import ir.ac.kntu.services.authentication.SellerAuthSer;
import ir.ac.kntu.ui.adminmenu.AdminMainMenu;
import ir.ac.kntu.ui.cusmenu.CusMainMenu;
import ir.ac.kntu.ui.manager.ManagerMenu;
import ir.ac.kntu.ui.sellermenu.SellerMainMenu;
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
    private ManagerAuthSer managerAuthSer;
    private ManagerDB managerDB;

    public Navigate(CustomerDB customerDB, SellerDB sellerDB, AdminDB adminDB, ProductDB productDB, ManagerDB managerDB) {
        this.customerDB = customerDB;
        this.sellerDB = sellerDB;
        this.adminDB = adminDB;
        this.productDB = productDB;
        this.managerDB = managerDB;
    }

    public void setServices() {//add necessary services
        adminAuthSer = new AdminAuthSer(adminDB);
        custAuthSer = new CustAuthSer(customerDB);
        sellerAuthSer = new SellerAuthSer(sellerDB);
        managerAuthSer = new ManagerAuthSer(managerDB);
    }

    public void sendRequest(Seller seller) {
        adminDB.getAuthRequest().add(new AuthRequest(seller));
    }

    public User leadToSignUP(Map<String, String> info, String user) {
        switch (user.toLowerCase()) {
            case "seller" -> {
                Seller seller = sellerAuthSer.signUp(info);
                if (seller != null) {
                    String shopId = seller.getShopID();
                    PrintHelper.printInfo(" Your shop-ID is : " + shopId + ". ");
                    sendRequest(seller);
                }
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
                if ("email".equals(chosenPath)) {
                    return custAuthSer.loginByEmail(info);
                } else if ("phoneNumber".equals(chosenPath)) {
                    return custAuthSer.loginByPhoneNumber(info);
                }
            }
            case "seller" -> {
                return sellerAuthSer.login(info);
            }
            case "admin" -> {
                return adminAuthSer.login(info);
            }
            case "manager" -> {
                return managerAuthSer.login(info);
            }
            default -> {
                return null;
            }
        }
        return null;
    }

    public void decideForUser(User user) {

        if (user instanceof Customer) {
            CusControl cusControl = new CusControl((Customer) user, customerDB, sellerDB, adminDB, productDB);
            cusControl.setServices();
            CusMainMenu customerMainMenu = new CusMainMenu(cusControl);
            customerMainMenu.showPage();
        } else if (user instanceof Seller) {
            SellControl sellControl = new SellControl(adminDB,productDB, (Seller) user, customerDB);
            SellerMainMenu sellerMainMenu = new SellerMainMenu(sellControl);
            sellerMainMenu.showPage();
        } else if (user instanceof Manager) {
            ManControl manControl = new ManControl((Manager) user, managerDB, productDB, customerDB,sellerDB, adminDB);
            ManagerMenu managerMenu = new ManagerMenu(manControl);
            managerMenu.firstPage();
        } else {
            AdmControl admControl = new AdmControl(adminDB, customerDB, (Admin) user);
            AdminMainMenu adminMainMenu = new AdminMainMenu(admControl);
            adminMainMenu.showPage();
        }
    }

}
