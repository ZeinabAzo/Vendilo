package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.services.AdminAuthService;
import ir.ac.kntu.services.CustomerAuthService;
import ir.ac.kntu.services.SellerAuthService;

public class SellerController {
    private CustomerDB customerDB;
    private SellerDB sellerDB;
    private AdminDB adminDB;
    private ProductDB productDB;
    private AdminAuthService adminAuthService;
    private CustomerAuthService customerAuthService;
    private SellerAuthService sellerAuthService;

    public SellerController(CustomerDB customerDB, SellerDB sellerDB, AdminDB adminDB, ProductDB productDB){
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

}
