package ir.ac.kntu.services;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.data.ManagerDB;
import ir.ac.kntu.data.SellerDB;
import ir.ac.kntu.models.*;
import ir.ac.kntu.util.InputHelper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ir.ac.kntu.services.authentication.AuthService.isValidName;
import static ir.ac.kntu.services.authentication.AuthService.isValidPassword;
import static ir.ac.kntu.util.PrintHelper.printError;

public class ManagerService {

    private Manager manager;
    private ManagerDB managerDB;
    private CustomerDB customerDB;
    private SellerDB sellerDB;
    private AdminDB adminDB;

    public ManagerService(Manager manager, ManagerDB managerDB, CustomerDB customerDB, SellerDB sellerDB,
                          AdminDB adminDB){
        this.manager = manager;
        this.managerDB = managerDB;
        this.customerDB = customerDB;
        this.sellerDB = sellerDB;
        this.adminDB = adminDB;
    }

    public boolean createManager(String fName, String lName, String password) {
        if (!isValidName(new String[]{fName, lName})) {
            return false;
        }

        if (!isValidPassword(password)) {
            return false;
        }

        if (managerDB.exists(fName, lName)) {
            printError("Manager '" + fName + " " + lName + "' already exists");
            return false;
        }

        Manager newManager = new Manager(fName.trim(), lName.trim(), password.trim(), manager.getLevel());
        managerDB.addManager(newManager);
        return true;
    }

    public List<User> filterCusName(String name) {
        return customerDB.getCustomers().stream()
                .filter(customer -> getMaxSimilarity(name, customer) > 0.75)
                .sorted(Comparator.comparingDouble(
                                (Customer customer) -> getMaxSimilarity(name, customer))
                        .reversed())
                .collect(Collectors.toList());
    }

    private double getMaxSimilarity(String name, User user) {
        String fullName = user.getfName() + user.getlName();
        double fullNameSimilar = InputHelper.calculateSimilarity(name, fullName);
        double fNameSimilarity = InputHelper.calculateSimilarity(name, user.getfName());
        double lNameSimilarity = InputHelper.calculateSimilarity(name, user.getlName());
        return Math.max(fullNameSimilar, Math.max(fNameSimilarity, lNameSimilarity));
    }

    public List<User> filterSellName(String name) {
        return sellerDB.getSellers().stream()
                .filter(seller -> getMaxSimilarity(name, seller) > 0.75)
                .sorted(Comparator.comparingDouble(
                                (Seller seller) -> getMaxSimilarity(name, seller))
                        .reversed())
                .collect(Collectors.toList());
    }

    public List<User> filterAdmName(String name) {
        return adminDB.getAdmins().stream()
                .filter(admin -> getMaxSimilarity(name, admin) > 0.75)
                .sorted(Comparator.comparingDouble(
                                (Admin admin) -> getMaxSimilarity(name, admin))
                        .reversed())
                .collect(Collectors.toList());
    }

    public List<User> lowerManagers(Manager managerOrg) {
        return managerDB.getManagers().stream().filter(manager -> managerOrg.getLevel()>=manager.getLevel() )
                .sorted(Comparator.comparingInt(Manager::getLevel)).collect(Collectors.toList());
    }

    public void modifyName(String name, Manager toBeModified) {
        Manager target = managerDB.getManager(toBeModified);
        if (target != null) {
            target.setfName(name);
        } else {
            printError("Manager not found! : managerService->modifyName"); // a debugging massage
        }
    }

    public void modifyUserName(String username, Manager toBeModified) {
        Manager target = managerDB.getManager(toBeModified);
        if (target != null) {
            target.setlName(username);
        } else {
            printError("Manager not found! : managerService->modifyUserName"); // a debugging massage
        }
    }
}