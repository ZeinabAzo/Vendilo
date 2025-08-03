package ir.ac.kntu.ui.manager;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.ui.manager.sellermanagement.SellerManagement;
import ir.ac.kntu.ui.manager.usermanager.UserManagement;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class ManagerMenu {

    private ManControl manControl;

    public ManagerMenu(ManControl manControl){
        this.manControl = manControl;
    }

    public void firstPage(){

        showOptions();
        int option = ScannerWrapper.nextInt();

        switch (option){
            case 1 -> userManagement();
            case 2 -> sellerManagement();
            case 6 ->{
            }
            case 7 -> Exit.exit();
            default -> PrintHelper.printError("Invalid command, try again: ");
        }
    }

    private void sellerManagement() {
        SellerManagement sellerManagement = new SellerManagement(manControl);
        sellerManagement.firstPage();
    }

    private void userManagement() {
        UserManagement userManagement = new UserManagement(manControl);
        userManagement.showPage();
    }

    private void showOptions(){
        PrintHelper.upperBorder("   THIS IS YOUR TERRITORY SUPERIOR DUCK!  ");
        PrintHelper.option(1, "user management menu");
        PrintHelper.option(2, "seller management menu");
        PrintHelper.option(3, "customer management menu");
        PrintHelper.option(4, "create general discount code");
        PrintHelper.option(5, "general announcement");
        PrintHelper.option(6, "return");
        PrintHelper.option(7, "exit");
    }
}
