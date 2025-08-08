package ir.ac.kntu.controllers;

import ir.ac.kntu.data.*;
import ir.ac.kntu.enums.ReportType;
import ir.ac.kntu.models.*;
import ir.ac.kntu.services.ManagerService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManControl {

    private Manager manager;
    private CustomerDB customerDB;
    private SellerDB sellerDB;
    private AdminDB adminDB;
    private ManagerDB managerDB;
    private ManagerService managerService;

    public ManControl(Manager manager,ManagerDB managerDB, CustomerDB customerDB,
                      SellerDB sellerDB, AdminDB adminDB) {
        this.manager = manager;
        this.customerDB = customerDB;
        this.sellerDB = sellerDB;
        this.adminDB = adminDB;
        managerService = new ManagerService(manager, managerDB, customerDB, sellerDB, adminDB);
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

    public void createAdmin(String name, String username, String password, List<ReportType> type) {
        Admin admin = new Admin(name, username, password, type);
        adminDB.getAdmins().add(admin);
    }

    public void createManager(String name, String username, String password) {
        Manager newManager = new Manager(name, username, password, manager.getLevel());
        managerDB.addManager(newManager);
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

    public double cusActivityCheck(Customer customer) {
        double amount = 0;
        for(Cart cart : customer.getCarts()){
            if(cart.isPurchased() && ChronoUnit.DAYS.between(cart.getDatePurchased(), LocalDate.now()) < 30){
                amount += cart.getAmountPurchased();
            }
        }
        return amount;
    }

    public void giftDiscount(Customer customer, Discount discount, String massage) {
        DiscountNotif discountNotif = new DiscountNotif(massage, manager.getlName(), discount);
        customer.sendNotification(discountNotif);
    }

    public  void giftPublicDiscount(Discount discount, String massage){
        DiscountNotif discountNotif = new DiscountNotif(massage, manager.getlName(), discount);
        for (Customer customer : customerDB.getCustomers()){
            customer.sendNotification(discountNotif);
        }
    }

    public void sendToAll(String massage) {
        Notification notification = new Notification(massage, manager.getlName());
        for (Customer customer : customerDB.getCustomers()){
            customer.sendNotification(notification);
        }
    }

    public Admin getAdmin(Admin admin) {
        return adminDB.getAdmins().stream().filter(admin1 -> admin1==admin).findFirst().orElse(null);
    }
}