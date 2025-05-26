package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.SellControl;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class SellerMainMenu {

    private SellControl sellerController;

    public SellerMainMenu(SellControl sellerController) {
        this.sellerController = sellerController;
    }

    public void showPage() {
        while (true) {
            PrintHelper.upperBorder("Seller profile");
            PrintHelper.option(1, "Products");
            PrintHelper.option(2, "Wallet");
            PrintHelper.option(3, "Orders");
            PrintHelper.option(4, "return");
            PrintHelper.lowerBorder("Seller profile");
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> {
                    SellProdMenu productSellerMenu = new SellProdMenu(sellerController);
                    productSellerMenu.productOptions();
                }
                case 4 -> {
                    return;
                }

            }
        }

    }


}
