package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.services.AdminAuthService;
import ir.ac.kntu.services.CustomerAuthService;
import ir.ac.kntu.services.SearchProducts;
import ir.ac.kntu.services.SellerAuthService;

public class CustomerController {

    private ProductDB productDB;
    private Customer customer;
    private SearchProducts searchProducts;

    public CustomerController(Customer customer, ProductDB productDB){
        this.customer=customer;
        this.productDB=productDB;
    }

    public void setServices(){//add necessary services
        SearchProducts searchProducts=new SearchProducts(productDB);
    }

}