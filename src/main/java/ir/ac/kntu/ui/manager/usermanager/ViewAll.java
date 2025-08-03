package ir.ac.kntu.ui.manager.usermanager;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.models.User;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

import java.util.ArrayList;
import java.util.List;

public class ViewAll {
    private ManControl manControl;

    public ViewAll(ManControl manControl){
        this.manControl = manControl;
    }

    public User viewList() {
        PrintHelper.ask("Oop—bossy! Which users are we side-eyeing today? ");
        PrintHelper.option(1, "customers");
        PrintHelper.option(2, "sellers");
        PrintHelper.option(3, "admins");
        PrintHelper.option(4, "managers, those below you");
        PrintHelper.option(5, "return");
        int option = ScannerWrapper.nextInt();

        List<User> users = new ArrayList<>();

        switch (option){
            case 1 -> users = customerFilter();
            case 2 -> users = sellerFilter();
            case 3 -> users = adminFilter();
            case 4 -> users = managerFilter();
            case 5 ->{
                return null;
            }
            default -> PrintHelper.printError("wrong choice");
        }

        int chosen = SplitDisplay.show(users);
        assert users != null;
        return users.get(chosen);
    }

    private List<User> managerFilter() {
        PrintHelper.printInfo("I got tired of filtering, these are all managers below you:");
        return manControl.filterMangs();
    }

    private List<User> adminFilter() {
        PrintHelper.ask("Choose how you want to proceed: ");
        PrintHelper.option(1, "filter by name");
        PrintHelper.option(2, "no filter");
        PrintHelper.option(3, "return");
        int option = ScannerWrapper.nextInt();

        switch (option){
            case 1 ->{
                PrintHelper.ask("Enter the name, be gentle we'll find them");
                String name = ScannerWrapper.nextLine();
                return manControl.filterAdmName(name);
            }
            case 2 ->{
                return manControl.getAllAdms();
            }
            case 3 ->{
                return null;
            }
            default -> PrintHelper.printError("wrong choice");
        }
        return null;
    }

    private List<User> sellerFilter() {
        PrintHelper.ask("Choose how you want to proceed: ");
        PrintHelper.option(1, "filter by shopID");
        PrintHelper.option(2, "filter by name");
        PrintHelper.option(3, "no filter");
        PrintHelper.option(4, "return");
        int option = ScannerWrapper.nextInt();

        switch (option){
            case 1 -> {
                PrintHelper.ask("Enter the specific shop's id");
                String shopID = ScannerWrapper.nextLine();
                return manControl.filterSellShop(shopID);
            }
            case 2 -> {
                PrintHelper.ask("Enter the name, be gentle we'll find them");
                String name = ScannerWrapper.nextLine();
                return manControl.filterSellName(name);
            }
            case 3 -> {
                return manControl.getAllSell();
            }
            case 4 ->{
                return null;
            }
            default -> PrintHelper.printError("wrong choice");
        }
        return null;
    }

    private List<User> customerFilter() {
        PrintHelper.ask("Choose how you want to proceed: ");
        PrintHelper.option(1, "filter by email");
        PrintHelper.option(2, "filter by name");
        PrintHelper.option(3, "no filter");
        PrintHelper.option(4, "return");
        int option = ScannerWrapper.nextInt();

        switch (option){
            case 1 -> {
                PrintHelper.ask("Enter the specific email");
                String email = ScannerWrapper.nextLine();
                return manControl.filterCusEmail(email);
            }
            case 2 -> {
                PrintHelper.ask("Enter the name, be gentle we'll find them");
                String name = ScannerWrapper.nextLine();
                PrintHelper.printInfo("We will show you up to 75% similar names-" +
                        "whether its first or last or full name");
                return manControl.filterCusName(name);
            }
            case 3 -> {
                return manControl.getAllCus();
            }
            case 4 ->{
                return null;
            }
            default -> PrintHelper.printError("wrong choice");
        }
        return null;
    }

}
