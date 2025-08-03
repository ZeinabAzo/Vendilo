package ir.ac.kntu.controllers;

import ir.ac.kntu.data.*;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Manager;
import ir.ac.kntu.models.User;
import ir.ac.kntu.services.ManagerService;
import ir.ac.kntu.services.SearchProducts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManControl {

    private Manager manager;
    private ProductDB productDB;
    private CustomerDB customerDB;
    private SellerDB sellerDB;
    private AdminDB adminDB;
    private SearchProducts searchProducts;
    private ManagerDB managerDB;
    private ManagerService managerService;

    public ManControl(Manager manager,ManagerDB managerDB, ProductDB productDB, CustomerDB customerDB, 
                      SellerDB sellerDB, AdminDB adminDB) {
        this.manager = manager;
        this.productDB = productDB;
        this.customerDB = customerDB;
        this.sellerDB = sellerDB;
        this.adminDB = adminDB;
        managerService = new ManagerService(manager, managerDB, customerDB, sellerDB, adminDB);
        this.searchProducts = new SearchProducts(productDB);
    }

    public List<User> filterCusEmail(String email) {
        return new ArrayList<>((Collection) customerDB.findByEmail(email));
    }

    public List<User> filterCusName(String name) {
        return managerService.filterCusName(name);
    }

    public List<User> getAllCus() {
        return new ArrayList<>(customerDB.getCustomers());
    }

    public List<User> filterSellShop(String shopID) {
        return new ArrayList<>((Collection) sellerDB.findByShopID(shopID));
    }

    public List<User> filterSellName(String name) {
        return managerService.filterSellName(name);
    }

    public List<User> getAllSell() {
        return new ArrayList<>(sellerDB.getSellers());
    }

    public List<User> filterAdmName(String name) {
        return managerService.filterAdmName(name);
    }

    public List<User> getAllAdms() {
        return new ArrayList<>(adminDB.getAdmins());
    }

    public List<User> filterMangs() {
        return managerService.lowerManagers(manager);
    }

    public void createAdmin(String name, String username, String password) {
        Admin admin = new Admin(name, username, password);
        adminDB.addAdmin(admin);
    }

    public void createManager(String name, String username, String password) {
        Manager newManager = new Manager(name, username, password, manager.getLevel());
        managerDB.addManager(manager);
    }

    public void modifyManName(String name, Manager toBeModified) {
        managerService.modifyName(name, toBeModified);
    }

    public void modifyManUserName(String username, Manager toBeModified) {
        managerService.modifyUserName(username, toBeModified);
    }

    public void banManager(Manager manager) {
        manager.setActive(false);
    }

    public void banCustomer(Customer customer) {
        customer.setActive(false);
    }

    public void modifyCusName(String name, Customer customer) {
        customer.setfName(name);
    }

    public void modifyCuslName(String username, Customer customer) {
        customer.setlName(username);
    }
}