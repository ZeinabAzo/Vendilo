package ir.ac.kntu.ui.manager;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.enums.DiscountType;
import ir.ac.kntu.models.Discount;
import ir.ac.kntu.ui.manager.customermanagement.CustomerManagement;
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
            case 3 -> customerManagement();
            case 4 -> createDis();
            case 5 -> sendToAll();
            case 6 ->{
            }
            case 7 -> Exit.exit();
            default -> PrintHelper.printError("Invalid command, try again: ");
        }
    }

    private void sendToAll() {
        PrintHelper.ask("What massage do you want to send?");
        String massage = ScannerWrapper.nextLine();
        manControl.sendToAll(massage);
    }

    private void createDis() {
        PrintHelper.ask("What kind of discount are you planning to gift?");
        PrintHelper.option(1, "General");
        PrintHelper.option(2,"Special");
        PrintHelper.option(3, "return");
        int chosen = ScannerWrapper.nextInt();
        DiscountType discountType = null;
        switch (chosen){
            case 1 -> discountType = DiscountType.GENERAL;
            case 2 -> discountType = DiscountType.SPECIAL;
            case 3 -> {
                return;
            }
            default -> PrintHelper.printError("Wrong input");
        }
        PrintHelper.ask("How much would it worth?");
        double value = ScannerWrapper.nextDouble();
        Discount discount = new Discount(discountType, value, 1);
        PrintHelper.ask("What would your massage be?");
        String massage = ScannerWrapper.nextLine();
        manControl.giftPublicDiscount(discount, massage);
    }

    private void customerManagement() {
        CustomerManagement cusManagement = new CustomerManagement(manControl);
        cusManagement.firstPage();
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
