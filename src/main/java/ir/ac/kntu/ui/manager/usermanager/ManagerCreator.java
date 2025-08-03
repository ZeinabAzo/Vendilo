package ir.ac.kntu.ui.manager.usermanager;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;

import static ir.ac.kntu.services.authentication.AuthService.isValidName;
import static ir.ac.kntu.services.authentication.AuthService.isValidPassword;

public class ManagerCreator {

    private ManControl manControl;

    public ManagerCreator(ManControl manControl){
        this.manControl = manControl;
    }

    public void createManager() {
        PrintHelper.miniUpperBorder("You need to fill information for the new manager");
        PrintHelper.ask("Enter name: ");
        String name = ScannerWrapper.nextLine();
        PrintHelper.ask("Enter username: ");
        String username = ScannerWrapper.nextLine();
        PrintHelper.ask("Enter password: ");
        String password = ScannerWrapper.nextLine();
        String[] names = new String[]{name, username};
        if(!isValidName(names) || !isValidPassword(password)){
            PrintHelper.printError("incorrect name or password");
            return;
        }
        manControl.createManager(name, username, password);
        PrintHelper.printSuccess(" New manager was created successfully! ");
    }

}
