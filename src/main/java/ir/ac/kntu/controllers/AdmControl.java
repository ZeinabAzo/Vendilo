package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.services.SearchProducts;

public class AdmControl {

    private AdminDB adminDB;
    private Admin admin;
    private ProductDB productDB;
    private SearchProducts searchProducts;

    public AdmControl(AdminDB adminDB, ProductDB productDB, Admin admin) {
        this.adminDB = adminDB;
        this.productDB = productDB;
        this.admin = admin;
    }

    public void setServices() {//add necessary services
        searchProducts = new SearchProducts(productDB);
    }


}