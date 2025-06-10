package ir.ac.kntu.ui.cusmenu;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.HashMap;

public class VendiloPlusMen {
    private CusControl cusControl;

    public VendiloPlusMen(CusControl cusControl) {
        this.cusControl = cusControl;
    }

    public void showPage(){
        if(cusControl.hasVendiloPlus()){
            showMainPage();
        }else{
            PrintHelper.printError("Sorry dear, here we have a VIP option you didn't pay for X_X");
            handleSignIn();
        }
    }

    private void handleSignIn() {
        PrintHelper.ask("THESE ARE YOUR OPTIONS NOW:");
        PrintHelper.option(1, "buy vendilo plus subscription ");
        PrintHelper.option(2, "return and forget it(we recommend you buy it though!)");
        PrintHelper.option(3, "exit");
        int choice = ScannerWrapper.nextInt();
        switch (choice) {
            case 1 -> subscribe();
            case 2 -> {
            }
            case 3 -> Exit.exit();
            default -> PrintHelper.printError("Invalid command");
        }
    }

    private void subscribe() {
        PrintHelper.miniUpperBorder("EXCELLENT CHOICE!!! ;) now come on- Choose your plan :");
        PrintHelper.option(1, "one month plan: 99.9$");
        PrintHelper.option(2, "three months plan: 259.9$");
        PrintHelper.option(3, "yearly subscription: 999.9$");
        PrintHelper.option(4, "return");
        PrintHelper.option(5 , "exit");
        PrintHelper.miniLowerBorder("EXCELLENT CHOICE!!! ;) now come on- Choose your plan :");
        int choice = ScannerWrapper.nextInt();

        switch (choice){
            case 1, 2, 3 -> buyIt(choice);
            case 4 ->{
            }
            case 5 -> Exit.exit();
            default -> PrintHelper.printError("Whoosh..! fasten your seat belt cause we are falling back!");
        }
    }

    private void buyIt(int choice) {
        HashMap<Integer, Double> payments = new HashMap<>();
        payments.put(1, 99.9);
        payments.put(2, 259.9);
        payments.put(3, 999.9);

        boolean success = cusControl.subscribe(payments.get(choice));
        if (success){
            PrintHelper.printSuccess("WELCOME YOU SPECIAL ONE! " +
                    "you're gonna experience vendilo a lot smoother now");
            showMainPage();
        }else{
            PrintHelper.printError("sorry honey, you are not wealthy enough!");
        }
    }

    private void showMainPage() {
        boolean goOn = true;
        while (goOn){
            PrintHelper.upperBorder("HI THIS IS VENDILO PLUS");
            PrintHelper.ask("How can I help you? ");
            //todo: continue on writing this.
        }
    }
}
