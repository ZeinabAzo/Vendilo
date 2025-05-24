package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.SellerController;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class SellerMainMenu {

    private SellerController sellerController;

    public SellerMainMenu(SellerController sellerController){
        this.sellerController=sellerController;
    }

    public void showPage(){
        while (true){
            PrintHelper.upperBorder("Seller profile");
            PrintHelper.option(1, "Products");
            PrintHelper.option(2, "Wallet");
            PrintHelper.option(3, "Orders");
            PrintHelper.option(4, "return");
            PrintHelper.lowerBorder("Seller profile");
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> {
                    ProductSellerMenu productSellerMenu = new ProductSellerMenu(sellerController);
                    productSellerMenu.productOptions();
                }
                case 4 -> {
                    return;
                }

            }
        }

    }


}
