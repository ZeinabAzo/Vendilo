package ir.ac.kntu.ui;

import ir.ac.kntu.controllers.AuthController;

import java.util.Scanner;

public class LoginPage extends Page{

    private Page userMenu;
    private Scanner scanner=new Scanner(System.in);

    public LoginPage(Page previousPage, Page userMenu){
        super(previousPage);
        this.userMenu=userMenu;
    }

    public void loginMenu(){

        printAnnouncement("Welcome to Vendilo. Please select your role to log in:");
        System.out.println("1. Admin ");
        System.out.println("2. Seller ");
        System.out.println("3. Customer ");
        System.out.println("4. Return ");
        System.out.println("5. Exit ");

        int choice=scanner.nextInt();
        switch (choice){
            case 1 -> {
            }
            // ...
            default -> {
                printError("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private boolean case1(){

        System.out.println("Enter your name:");
        String name=scanner.nextLine();
        System.out.println("Enter your username");
        String username=scanner.nextLine();
        System.out.println("Enter your password");
        String password=scanner.nextLine();
        return false;
    }
}
