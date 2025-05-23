package ir.ac.kntu.controllers;

import ir.ac.kntu.data.ProductDB;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.services.SearchProducts;

import java.util.HashMap;

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

    public HashMap<Seller, Product> searchByName(String name){
        return searchProducts.searchProductByName(name);
    }
}