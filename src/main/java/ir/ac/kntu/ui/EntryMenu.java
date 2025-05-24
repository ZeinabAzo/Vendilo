package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.Navigate;
import ir.ac.kntu.models.User;
import ir.ac.kntu.util.PrintHelper.consoleColors;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.HashMap;

public class EntryMenu {

    private Navigate navigator;

    public EntryMenu(Navigate navigator){
        this.navigator=navigator;
    }

    public void entry(){

        while(true){

            System.out.print(consoleColors.BRIGHT_BLUE + "◸—————" + "    ⁂Welcome to " + consoleColors.RESET );
            System.out.print(consoleColors.PURPLE+ "VENDILO");
            System.out.println(consoleColors.BRIGHT_BLUE + "⁂    —————◹" + consoleColors.RESET);
            PrintHelper.option(1, "Log in");
            PrintHelper.option(2, "Sign up");
            PrintHelper.option(3, "Exit");
            PrintHelper.lowerBorder("welcome to vendilo");

            User user=new User();
            int choice= ScannerWrapper.nextInt();

            switch (choice){
                case 1 -> {
                    user = loginMenu();
                    PrintHelper.printSuccess("Entering your profile successfully ...");
                    navigator.decideForUser(user);
                }
                case 2 -> {
                    user = signupMenu();
                    PrintHelper.printSuccess("Entering your profile successfully ...");
                    navigator.decideForUser(user);
                }
                case 3 -> {
                    PrintHelper.ask("Are you sure you want to exit? (yes/no)");
                    String confirm = ScannerWrapper.nextLine().toLowerCase();
                    if (confirm.equals("yes") || confirm.equals("y")) {
                        return;
                    }
                }
                default -> PrintHelper.printError("Invalid command!");
            }


        }
    }

    public User signupMenu(){

        boolean run=true;
        while(run){
            PrintHelper.upperBorder("Choose your role to sign up");
            PrintHelper.option(1, "Customer");
            PrintHelper.option(2, "Seller");
            PrintHelper.option(3, "return");
            PrintHelper.option(4, "exit");
            int choice = ScannerWrapper.nextInt();

            HashMap<String, String> info = new HashMap<>();

            switch (choice) {
                case 1 -> {
                    info = PrintHelper.askForInformation("customer");
                    System.out.println(info);
                    return navigator.leadToSignUP(info, "customer");
                }
                case 2 -> {
                    info = PrintHelper.askForInformation("seller");
                    return navigator.leadToSignUP(info, "seller");
                }
                case 3 -> {
                    return null;
                }
                case 4 -> run=false;
                default -> PrintHelper.printError("Invalid choice! try again:");
            }

        }
        return null;
    }

    public User loginMenu(){

        boolean run=true;
        while(run) {
            PrintHelper.upperBorder("Choose your role to sign up");
            PrintHelper.option(1, "Customer");
            PrintHelper.option(2, "Seller");
            PrintHelper.option(3, "Admin");
            PrintHelper.option(4, "return");
            PrintHelper.option(5, "exit");
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> {
                    return handleCustomerLogin();
                }
                case 2 -> {
                    return handleSellerLogin();
                }
                case 3 -> {
                    return handleAdminLogin();
                }
                case 4 -> {
                    return null;
                }
                case 5 -> run = false;
                default -> PrintHelper.printError("Invalid choice! try again:");
            }
        }
        return null;
    }

    public User handleCustomerLogin(){
        PrintHelper.miniUpperBorder("Choose your way to login");
        PrintHelper.option(1, "Email");
        PrintHelper.option(2, "Phone number");
        PrintHelper.option(3, "return");
        PrintHelper.miniLowerBorder("Choose your way to login");
        int chosen = ScannerWrapper.nextInt();

        HashMap<String, String> info = new HashMap<>();
        User user;

        switch (chosen) {
            case 1 -> {
                PrintHelper.option(1, "Enter your email: ");
                String email = ScannerWrapper.nextLine();
                info.put("email", email);
                PrintHelper.option(2, "Enter your password: ");
                String password = ScannerWrapper.nextLine();
                info.put("password", password);

                return navigator.login("customer", info, "email");
            }
            case 2 -> {
                PrintHelper.option(1, "Enter your phone number: ");
                String phoneNum = ScannerWrapper.nextLine();
                info.put("phoneNumber", phoneNum);
                PrintHelper.option(2, "Enter your password: ");
                String password = ScannerWrapper.nextLine();
                info.put("password", password);

                return navigator.login("customer", info, "phoneNumber");
            }
            case 3 ->{
                return null;
            }
            default -> PrintHelper.printError("Invalid choice.");
        }
        return null;
    }

    public User handleSellerLogin(){

        HashMap<String, String> info = new HashMap<>();

        PrintHelper.miniUpperBorder("Enter your information");
        PrintHelper.option(1, "Enter your Shop ID");
        String shopID=ScannerWrapper.nextLine();
        info.put("shopID", shopID);
        PrintHelper.option(2, "Enter your password");
        String password=ScannerWrapper.nextLine();
        info.put("password", password);

        return navigator.login("seller", info, null);
    }

    public User handleAdminLogin(){
        HashMap<String, String> info = new HashMap<>();

        PrintHelper.miniUpperBorder("Enter your information");
        PrintHelper.option(1, "Enter your name");
        String name=ScannerWrapper.nextLine();
        info.put("name", name);
        PrintHelper.option(2, "Enter your username");
        String username=ScannerWrapper.nextLine();
        info.put("username", username);
        PrintHelper.option(3, "Enter your password");
        String password=ScannerWrapper.nextLine();
        info.put("password", password);

        return navigator.login("admin", info, null);
    }
}
