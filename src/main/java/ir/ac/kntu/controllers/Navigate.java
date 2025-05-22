package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.models.User;
import ir.ac.kntu.services.AdminAuthService;
import ir.ac.kntu.services.CustomerAuthService;
import ir.ac.kntu.services.SellerAuthService;

import java.util.HashMap;

public class Navigate {

    private CustomerDB customerDB;
    private SellerDB sellerDB;
    private AdminDB adminDB;
    private ProductDB productDB;
    private AdminAuthService adminAuthService;
    private CustomerAuthService customerAuthService;
    private SellerAuthService sellerAuthService;

    public Navigate(CustomerDB customerDB, SellerDB sellerDB, AdminDB adminDB, ProductDB productDB){
        this.customerDB=customerDB;
        this.sellerDB=sellerDB;
        this.adminDB=adminDB;
        this.productDB=productDB;
    }

    public void setServices(){//add necessary services
        adminAuthService=new AdminAuthService(adminDB);
        customerAuthService=new CustomerAuthService(customerDB);
        sellerAuthService=new SellerAuthService(sellerDB);
    }

    public User leadToSignUP(HashMap<String, String> info , String user){
        switch (user.toLowerCase()){
            case "seller" -> {
                return sellerAuthService.signUp(info);
            }
            case "customer" -> {
                return customerAuthService.signUp(info);
            }
            default -> {
                return null;
            }
        }
    }

    public User login(String user, HashMap<String, String> info, String chosenPath){
        switch (user) {
            case "customer" -> {
                if (chosenPath.equals("email")) {
                    return customerAuthService.loginByEmail(info);
                } else if (chosenPath.equals("phoneNumber")) {
                    return customerAuthService.loginByPhoneNumber(info);
                }
            }
            case "seller" -> {
                return sellerAuthService.login(info);
            }
            case "admin" -> {
                return adminAuthService.login(info);
            }
            default -> {
                return null;
            }
        }
        return null;
    }

    public void decideForUser(User user){

        if(user instanceof Customer){

        }else if(user instanceof Seller){

        }else{
            AdminController adminController=new AdminController(customerDB, sellerDB, adminDB, productDB);
        }
    }

}
