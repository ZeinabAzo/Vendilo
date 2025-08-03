package ir.ac.kntu.ui.cusmenu;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.models.Complaint;
import ir.ac.kntu.util.*;

import java.util.List;

public class CusMainMenu {

    private CusControl cusControl;

    public CusMainMenu(CusControl cusControl) {
        this.cusControl = cusControl;
    }


    public void showPage() {
        while (true) {
            showOptions();
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> searchOptions();
                case 2 -> showAllCarts();
                case 3 -> addressOptions();
                case 4 -> walletMenu();
                case 5 -> orders();
                case 6 -> setting();
                case 7 -> discountUI(); //TODO:
                case 8 -> vendiloPlus();
                case 9 -> notifications();
                case 10 -> support();
                case 11 -> {
                    return;
                }
                case 12 -> {
                    deleteAccount();
                    return;
                }
                default -> PrintHelper.printError("Invalid option! Try again.");
            }
        }
    }

    private void deleteAccount() {
        PrintHelper.surprise("OOPS, DELETED YOUR ACCOUNT SUCCESSFULLY! ○( ＾皿＾)っ Hehehe…");
        PrintHelper.ask("if you think you messed up just enter the number 0");
        int isSorry = ScannerWrapper.nextInt();
        if(isSorry == 0){
            PrintHelper.printInfo("it's never late to feel sorry you little explorer!");
            PrintHelper.printSuccess("your account is back.");// it was never deleted to be honest
        }else{
            PrintHelper.printInfo("your loss!");
            cusControl.deleteAccount();
        }
    }

    private static void showOptions() {
        PrintHelper.upperBorder("     Customer profile     ");
        PrintHelper.option(1, "Search products");
        PrintHelper.option(2, "Carts");
        PrintHelper.option(3, "Addresses");
        PrintHelper.option(4, "Wallet");
        PrintHelper.option(5, "Orders");
        PrintHelper.option(6, "Setting");
        PrintHelper.option(7, "discount codes");
        PrintHelper.option(8,"VENDILO+");
        PrintHelper.option(9, "notifications");
        PrintHelper.option(10, "Support");
        PrintHelper.option(11, "Return");
        PrintHelper.option(12, "Dare to choose me? 💀");
        PrintHelper.lowerBorder("     Customer profile     ");
    }

    private void notifications() {
        NotifMen notifMen = new NotifMen(cusControl);
        notifMen.showPage();
    }

    private void vendiloPlus(){
        VendiloPlusMen vendiloPlusMen = new VendiloPlusMen(cusControl);
        vendiloPlusMen.showPage();
    }

    private void discountUI() {
        CusDisMen cusDisMen = new CusDisMen(cusControl);
        cusDisMen.showPage();
    }

    private void walletMenu() {
        CusWalletM cusWalletM = new CusWalletM(cusControl);
        cusWalletM.walletMenu();
    }

    private void addressOptions() {
        CusSearchMenu.AddressCus addressCus = new CusSearchMenu.AddressCus(cusControl);
        addressCus.firstShow();
    }

    private void showAllCarts() {
        CusCartMenu cusCartMenu = new CusCartMenu(cusControl);
        cusCartMenu.showAllCarts();
    }

    private void searchOptions() {
        CusSearchMenu cusSearchMenu = new CusSearchMenu(cusControl);
        cusSearchMenu.firstPage();
    }

    private void orders() {
        PrintHelper.miniUpperBorder("Choose a purchased cart");
        cusControl.showPurchCart();
    }

    private void setting() {

        PrintHelper.printInfo(cusControl.getCustomer().toString());
        PrintHelper.ask("Do you want to change anything?");
        PrintHelper.option(1, "yes, let's change some things ");
        PrintHelper.option(2, "Nope, return");
        PrintHelper.option(3, "exit");
        int choice = ScannerWrapper.nextInt();

        switch (choice) {
            case 1 -> editSomething();
            case 2 -> {
            }
            case 3 -> Exit.exit();
            default -> PrintHelper.printError("Invalid command");
        }
    }

    private void editSomething() {
        boolean goOn = true;
        do{
            PrintHelper.ask("what to change?");
            PrintHelper.option(1, "first name");
            PrintHelper.option(2, "last name");
            PrintHelper.option(3, "email");
            PrintHelper.option(4, "password");
            PrintHelper.option(5, "phone number");
            PrintHelper.option(6, "return");
            PrintHelper.option(7, "exit");
            int choice = ScannerWrapper.nextInt();

            switch (choice){
                case 1 -> cusControl.editfName();
                case 2 -> cusControl.editlName();
                case 3 -> cusControl.editEmail();
                case 4 -> cusControl.editPassword();
                case 5 -> cusControl.editPhoneNum();
                case 6 -> goOn=false;
                case 7 -> Exit.exit();
                default -> PrintHelper.printError("Invalid command ");
            }
        }while (goOn);
    }

    private void support() {
        PrintHelper.miniUpperBorder("your previous reports:");
        List<Complaint> reports = cusControl.getPreReports();
        SplitDisplay.show(reports);
        PrintHelper.miniLowerBorder("your previous reports:");
        PrintHelper.ask("Whats wrong bro?(in case nothing is wrong type 'return')");
        String complaint = ScannerWrapper.nextLine();
        if(InputHelper.calculateSimilarity(complaint, "return")<0.75){
            cusControl.sendComplaint(complaint);
        }
    }
}
