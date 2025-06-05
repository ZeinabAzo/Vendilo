package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.models.Transaction;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

import java.time.LocalDate;
import java.util.List;

public class CusWalletM {

    private CusControl cusControl;

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
        PrintHelper.ask("Do you wanna filter anything? ");
        PrintHelper.option(1, "yup I need a time filter");
        PrintHelper.option(2, "no I want to see everything ... ");
        PrintHelper.option(3, "shut the fuck up and return");
        int choice = ScannerWrapper.nextInt();

        switch (choice) {
            case 1 -> transFilter();
            case 2 -> cusControl.showTransactions();
            case 3 -> {
            }
            default -> PrintHelper.printError("Invalid command");
        }

    }

    private void transFilter() {//change for calendar later
        PrintHelper.ask("Enter primary date(yyyy-MM-dd): ");
        String primDate = ScannerWrapper.nextLine();
        PrintHelper.ask("Enter secondary date(yyyy-MM-dd): ");
        String secDate = ScannerWrapper.nextLine();
        LocalDate primaryDate = null, secondaryDate = null;

        try {
            primaryDate = LocalDate.parse(primDate);
            secondaryDate = LocalDate.parse(secDate);
            if (!primaryDate.isBefore(secondaryDate)) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            PrintHelper.printError("Something went wrong! WE ARE SORRY ");
        }

        List<Transaction> filtered = cusControl.getFiltered(primaryDate, secondaryDate);
        SplitDisplay.show(filtered);

    }

    private void chargeBalance() {

        PrintHelper.ask("How much do you want to charge? ");
        double amount = ScannerWrapper.nextDouble();
        boolean success = cusControl.chargeBalance(amount);
        if (!success) {
            PrintHelper.printError("Something went wrong.");
        }
    }
}
