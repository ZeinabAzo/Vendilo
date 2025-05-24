package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.services.AdminAuthService;
import ir.ac.kntu.services.CustomerAuthService;
import ir.ac.kntu.services.SearchProducts;
import ir.ac.kntu.services.SellerAuthService;

import java.util.ArrayList;

public class AdminController {

    private AdminDB adminDB;
    private Admin admin;
    private ProductDB productDB;
    private SearchProducts searchProducts;

    public AdminController( AdminDB adminDB, ProductDB productDB, Admin admin){
        this.adminDB=adminDB;
        this.productDB=productDB;
        this.admin=admin;
    }

    public void setServices(){//add necessary services
        searchProducts= new SearchProducts(productDB);
    }




}