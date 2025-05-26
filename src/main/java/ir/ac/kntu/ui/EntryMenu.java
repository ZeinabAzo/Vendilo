package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.Navigate;
import ir.ac.kntu.models.User;
import ir.ac.kntu.util.PrintHelper.ConsoleColors;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.HashMap;

public class EntryMenu {

    private Navigate navigator;

    public EntryMenu(Navigate navigator) {
        this.navigator = navigator;
    }

    public void entry() {

        while (true) {

            firstMassage();

            User user;
            int choice = ScannerWrapper.nextInt();

            switch (choice) {
                case 1 -> {
                    user = loginMenu();
                    decide(user);
                }
                case 2 -> {
                    user = signupMenu();
                    decide(user);
                }
                case 3 -> {
                    return;
                }
                case 4 -> exit();
                default -> PrintHelper.printError("Invalid command!");
            }


        }
    }

    private static void exit() {
        PrintHelper.ask("Are you sure you want to exit? (yes/hell no)");
        String confirm = ScannerWrapper.nextLine().toLowerCase();
        if (confirm.equals("yes") || confirm.equals("y")) {
            System.exit(0);
        }
    }

    private void decide(User user) {
        if (user != null) {
            PrintHelper.printSuccess("Entering your profile successfully ...");
            navigator.decideForUser(user);
        } else {
            PrintHelper.printError("try again you idiot");
        }
    }

    private static void firstMassage() {
        System.out.print(ConsoleColors.CYAN + "◸—————" + "    ⁂Welcome to " + ConsoleColors.RESET);
        System.out.print(ConsoleColors.PURPLE + "VENDILO");
        System.out.println(ConsoleColors.CYAN + "⁂    —————◹" + ConsoleColors.RESET);
        PrintHelper.option(1, "Log in");
        PrintHelper.option(2, "Sign up");
        PrintHelper.option(3, "Exit");
        PrintHelper.lowerBorder("welcome to vendilo");
    }

    public User signupMenu() {

        while (true) {
            int choice = getChoice();

            HashMap<String, String> info = new HashMap<>();

            switch (choice) {
                case 1 -> {
                    return getUser(info, "customer");
                }
                case 2 -> {
                    return getUser(info, "seller");
                }
                case 3 -> {
                    return null;
                }
                case 4 -> exit();
                default -> PrintHelper.printError("Invalid choice! try again:");
            }

        }
    }

    private User getUser(HashMap<String, String> info, String customer) {
        info = (HashMap<String, String>) PrintHelper.askForInformation(customer);
        return navigator.leadToSignUP(info, customer);
    }

    private static int getChoice() {
        PrintHelper.upperBorder("Choose your role to sign up");
        PrintHelper.option(1, "Customer");
        PrintHelper.option(2, "Seller");
        PrintHelper.option(3, "return");
        PrintHelper.option(4, "exit");
        return ScannerWrapper.nextInt();
    }

    public User loginMenu() {

        while (true) {
            int choice = getAnInt();

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
                case 5 -> exit();
                default -> PrintHelper.printError("Invalid choice! try again:");
            }
        }
    }

    private static int getAnInt() {
        PrintHelper.upperBorder("Choose your role to sign up");
        PrintHelper.option(1, "Customer");
        PrintHelper.option(2, "Seller");
        PrintHelper.option(3, "Admin");
        PrintHelper.option(4, "return");
        PrintHelper.option(5, "exit");
        return ScannerWrapper.nextInt();
    }

    public User handleCustomerLogin() {
        int chosen = getChosen();

        HashMap<String, String> info = new HashMap<>();

        switch (chosen) {
            case 1 -> {
                return getUser("Enter your email: ", info, "email");
            }
            case 2 -> {
                return getUser("Enter your phone number: ", info, "phoneNumber");
            }
            case 3 -> {
                return null;
            }
            case 4 -> exit();
            default -> PrintHelper.printError("Invalid choice.");
        }
        return null;
    }

    private User getUser(String massage, HashMap<String, String> info, String chosenPath) {
        PrintHelper.newLine();
        PrintHelper.ask(massage);
        String information = ScannerWrapper.nextLine();
        info.put(chosenPath, information);
        PrintHelper.ask("Enter your password: ");
        String password = ScannerWrapper.nextLine();
        info.put("password", password);

        return navigator.login("customer", info, chosenPath);
    }

    private static int getChosen() {
        PrintHelper.miniUpperBorder("Choose your way to login");
        PrintHelper.option(1, "Email");
        PrintHelper.option(2, "Phone number");
        PrintHelper.option(3, "return");
        PrintHelper.option(4, "exit");
        PrintHelper.miniLowerBorder("Choose your way to login");
        int chosen = ScannerWrapper.nextInt();
        return chosen;
    }

    public User handleSellerLogin() {

        HashMap<String, String> info = new HashMap<>();

        PrintHelper.newLine();
        PrintHelper.miniUpperBorder("Enter your information");
        PrintHelper.ask("Enter your Shop ID");
        String shopID = ScannerWrapper.nextLine();
        info.put("shopID", shopID);
        PrintHelper.ask("Enter your password");
        String password = ScannerWrapper.nextLine();
        info.put("password", password);

        return navigator.login("seller", info, null);
    }

    public User handleAdminLogin() {
        HashMap<String, String> info = new HashMap<>();

        PrintHelper.newLine();
        PrintHelper.miniUpperBorder("Enter your information");
        PrintHelper.ask("Enter your name");
        String name = ScannerWrapper.nextLine();
        info.put("name", name);
        PrintHelper.ask("Enter your username");
        String username = ScannerWrapper.nextLine();
        info.put("username", username);
        PrintHelper.ask("Enter your password");
        String password = ScannerWrapper.nextLine();
        info.put("password", password);

        return navigator.login("admin", info, null);
    }
}
