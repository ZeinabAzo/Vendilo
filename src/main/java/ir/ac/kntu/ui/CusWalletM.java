package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class CusWalletM {

    CusControl cusControl;

    public CusWalletM(CusControl cusControl) {
        this.cusControl = cusControl;
    }

    public void walletMenu() {
        while (true) {
            PrintHelper.miniUpperBorder(" $  Hi, I'm your wallet!  $");
            PrintHelper.option(1, "Show former transactions");
            PrintHelper.option(2, "Charge Balance");
            PrintHelper.option(3, "Return");
            PrintHelper.miniLowerBorder(" $  Hi, I'm your wallet!  $");

            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> showTransactions();
                case 2 -> chargeBalance();
                case 3 -> {
                    return;
                }
                default -> PrintHelper.printError("Invalid option!");
            }
        }
    }

    private void showTransactions() {

    }

    private void chargeBalance() {

        PrintHelper.printInfo("Charging wallet...");
    }
}
