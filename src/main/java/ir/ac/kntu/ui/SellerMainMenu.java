package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.SellControl;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

public class SellerMainMenu {

    private SellControl sellerController;

    public SellerMainMenu(SellControl sellerController) {
        this.sellerController = sellerController;
    }


    private boolean response() {
        return sellerController.response();
    }

    public void showPage() {
        if (authentication()) {
            return;
        }

        while (true) {
            PrintHelper.upperBorder("Seller profile");
            PrintHelper.option(1, "Products");
            PrintHelper.option(2, "Wallet");
            PrintHelper.option(3, "Orders");
            PrintHelper.option(4, "return");
            PrintHelper.option(5, "exit");
            PrintHelper.lowerBorder("Seller profile");
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> {
                    SellProdMenu productSellerMenu = new SellProdMenu(sellerController);
                    productSellerMenu.productOptions();
                }
                case 2 -> walletMenu();
                case 3 -> ordersMenu();
                case 4 -> {
                    return;
                }
                case 5 -> Exit.exit();
                default -> PrintHelper.printError("Invalid command");
            }
        }

    }

    private void ordersMenu() {

        PrintHelper.ask("This is the list of your orders: ");
        sellerController.showOrders();
    }

    private void walletMenu() {
        boolean goOn = true;
        while (goOn) {
            PrintHelper.miniUpperBorder("$$ HAHA time to check your profit! $$");
            PrintHelper.printInfo("your wallet's balance: " +
                    sellerController.getSeller().getWallet().getBalance());
            PrintHelper.option(1, "Withdraw (Enter the amount)");
            PrintHelper.option(2, "Return (Enter 'r')");
            PrintHelper.option(3, "Exit (Enter 'e')");
            String choice = ScannerWrapper.nextLine();

            switch (choice) {
                case "r" -> goOn = false;
                case "e" -> Exit.exit();
                default -> {
                    try {
                        double value = Double.parseDouble(choice);
                        sellerController.withdraw(value);
                    } catch (NumberFormatException e) {
                        PrintHelper.printError("Invalid command. Please enter a number, 'r' to return," +
                                " or 'e' to exit.");
                    }
                }
            }
        }
    }


    private boolean authentication() {
        String answer= sellerController.getResponse();
        if(!response()){
            PrintHelper.printError("You have not been authenticated by an admin yet.");
            if(answer!=null){
                PrintHelper.printInfo(answer);
            }
            return true;
        }
        if(answer!=null){
            PrintHelper.printInfo(answer);
        }
        return false;
    }


}
