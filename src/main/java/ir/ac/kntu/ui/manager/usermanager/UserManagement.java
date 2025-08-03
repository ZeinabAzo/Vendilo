package ir.ac.kntu.ui.manager.usermanager;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.models.*;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class UserManagement {

    private ManControl manControl;
    private ViewAll viewAll;
    private AdminCreator adminCreator;
    private ManagerCreator managerCreator;
    private AdminModMenu adminModMenu;

    public UserManagement(ManControl manControl){
        this.manControl = manControl;
        viewAll = new ViewAll(manControl);
        adminCreator = new AdminCreator(manControl);
        managerCreator = new ManagerCreator(manControl);
        adminModMenu = new AdminModMenu(manControl);
    }

    private void showOptions() {

        PrintHelper.miniUpperBorder("  Here you can do some magic !!  ");
        PrintHelper.option(1, "view users list");// I think this is not general
        PrintHelper.option(2, "create admin/manager");
        PrintHelper.option(3, "modify accounts");// allow to edit or block customer, admin or manager
        PrintHelper.option(4, "return");
        PrintHelper.option(5, "exit");
    }

    public void showPage() {
        while (true){
            showOptions();
            int option = ScannerWrapper.nextInt();

            switch (option) {
                case 1 -> viewAll.viewList();
                case 2 -> createAdMan();
                case 3 -> modifyAccounts();//todo
                case 4 -> {
                    return;
                }
                case 5 -> Exit.exit();
                default -> PrintHelper.printError("wrong choice");
            }
        }
    }

    private void modifyAccounts() {
        PrintHelper.printInfo("you got to choose on user to modify or ban(can't be sellers)");
        User toBeModified = viewAll.viewList();
        if(toBeModified instanceof Seller){
            PrintHelper.printError("This is a seller's account and you can not change it");
        }else if(toBeModified instanceof Manager manager){
            managerModBan(manager);
        } else if (toBeModified instanceof Customer customer) {
            cusModBan(customer);
        } else if (toBeModified instanceof Admin admin) {
            adminModification(admin);
        }else {
            PrintHelper.printError("Something went wrong with user's type declaration.");
        }

    }

    private void adminModification(Admin admin) {
        adminModMenu.firstPage();
    }


    private void managerModBan(Manager manager) {
        int choice = showModBanOp();
        switch (choice){
            case 1 -> modifyManager(manager);
            case 2 -> banManager(manager);
            case 3 -> {
            }
            default -> PrintHelper.printError("wrong choice");
        }
    }

    private void banManager(Manager manager) {
        PrintHelper.ask("Are you sure you want to BAN this manager?(yes/no)");
        String answer = ScannerWrapper.nextLine();
        if(!answer.equals("yes")){
            return;
        }
        manControl.banManager(manager);
    }

    private void modifyManager(Manager manager) {
        int choice = showModify();
        while (true){
            switch (choice){
                case 1 -> {
                    PrintHelper.ask("Enter new name: ");
                    String name = ScannerWrapper.nextLine();
                    manControl.modifyManName(name, manager);
                }
                case 2 ->  {
                    PrintHelper.ask("Enter new username: ");
                    String username = ScannerWrapper.nextLine();
                    manControl.modifyManUserName(username, manager);
                }
                case 3 ->{
                    return;
                }
                case 4 -> Exit.exit();
                default -> PrintHelper.printError("Wrong choice");
            }
        }
    }

    private int showModify() {
        PrintHelper.newLine();
        PrintHelper.option(1, "change name");
        PrintHelper.option(2, "change username");
        PrintHelper.option(3, "return");
        PrintHelper.option(4, "exit");
        return ScannerWrapper.nextInt();
    }

    private int showModBanOp(){
        PrintHelper.miniUpperBorder("What do you want to do with this?");
        PrintHelper.option(1, "Modify account");
        PrintHelper.option(2, "Ban account");
        PrintHelper.option(3, "never mind return");
        return ScannerWrapper.nextInt();
    }

    private void createAdMan() {
        PrintHelper.ask("What do you want to add to our team");
        PrintHelper.option(1, "admin");
        PrintHelper.option(2, "manager");
        PrintHelper.option(3, "return");
        PrintHelper.option(4, "exit");
        int option = ScannerWrapper.nextInt();

        while (true){
            switch (option){
                case 1 -> adminCreator.createAdmin();
                case 2 -> managerCreator.createManager();
                case 3 ->{
                    return;
                }
                case 4 -> Exit.exit();
            }
        }
    }

    private void cusModBan(Customer customer) {
        int choice = showModBanOp();
        switch (choice){
            case 1 -> modifyCustomer(customer);
            case 2 -> banCustomer(customer);
            case 3 -> {
            }
            default -> PrintHelper.printError("wrong choice");
        }
    }

    private void banCustomer(Customer customer) {
        PrintHelper.ask("Are you sure you want to BAN this customer?(yes/no)");
        String answer = ScannerWrapper.nextLine();
        if(!answer.equals("yes")){
            return;
        }
        manControl.banCustomer(customer);
    }

    private void modifyCustomer(Customer customer){
        PrintHelper.newLine();
        PrintHelper.option(1, "change name");
        PrintHelper.option(2, "change username");
        PrintHelper.option(3, "return");
        PrintHelper.option(4, "exit");
        int choice = ScannerWrapper.nextInt();
        while (true){
            switch (choice){
                case 1 -> {
                    PrintHelper.ask("Enter new name: ");
                    String name = ScannerWrapper.nextLine();
                    manControl.modifyCusName(name, customer);
                }
                case 2 ->  {
                    PrintHelper.ask("Enter new username: ");
                    String username = ScannerWrapper.nextLine();
                    manControl.modifyCuslName(username, customer);
                }
                case 3 ->{
                    return;
                }
                case 4 -> Exit.exit();
                default -> PrintHelper.printError("Wrong choice");
            }
        }

    }


}
