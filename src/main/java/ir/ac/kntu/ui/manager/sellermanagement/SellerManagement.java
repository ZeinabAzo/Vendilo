package ir.ac.kntu.ui.manager.sellermanagement;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.models.Seller;
import ir.ac.kntu.models.User;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

import java.util.ArrayList;
import java.util.List;

public class SellerManagement {
    private ManControl manControl;

    public SellerManagement(ManControl manControl){
        this.manControl = manControl;
    }

    public void firstPage() {
        PrintHelper.upperBorder(" Welcome to sellers's section ");
        PrintHelper.option(1, "show all sellers ");
        PrintHelper.option(2, "filter by shop ID ");
        PrintHelper.option(3, "return");
        PrintHelper.option(4, "exit");
        PrintHelper.lowerBorder(" Welcome to sellers's section ");

        int choice = ScannerWrapper.nextInt();
        List<User> sellers = new ArrayList<>();
        while(true){
            switch (choice){
                case 1 -> {
                    sellers = manControl.getAllSell();
                    getToWork(sellers);
                }
                case 2 -> {
                    PrintHelper.ask("Enter the shopID your searching for:");
                    String shopID = ScannerWrapper.nextLine();
                    sellers = manControl.filterSellShop(shopID);
                    getToWork(sellers);
                }
                case 3 -> {
                    return;
                }
                case 4 -> Exit.exit();
                default -> PrintHelper.printError("your choice is out of boundaries");
            }
        }
    }

    private void getToWork(List<User> sellers) {
        int choice = SplitDisplay.show(sellers);
        Seller seller = (Seller) sellers.get(choice);
        double preMonthActivity = seller.preMonthActivity();
        PrintHelper.printInfo("This seller: " + seller.getShopID() + " has made sales of: $"
                + String.format("%.2f", preMonthActivity) + " in the last month.");
        PrintHelper.ask("Are you planning to offer a bonus to this seller?(yes/no)");
        String answer = ScannerWrapper.nextLine().trim().toLowerCase();
        if("yes".equals(answer)){
            PrintHelper.ask("Enter bonuses amount: ");
            Double amount = ScannerWrapper.nextDouble();
            seller.getWallet().receiveBonus(amount);
            PrintHelper.printSuccess("  Done.  ");
        }
    }
}
