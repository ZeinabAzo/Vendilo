package ir.ac.kntu.ui.manager.usermanager;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.enums.ReportType;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.RTypeSelector;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.List;

import static ir.ac.kntu.services.authentication.AuthService.isValidName;
import static ir.ac.kntu.services.authentication.AuthService.isValidPassword;

public class AdminCreator {

    private ManControl manControl;

    public AdminCreator(ManControl manControl) {
        this.manControl = manControl;
    }

    public void createAdmin() {
        PrintHelper.miniUpperBorder("You need to fill information for the new admin");
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
        List<ReportType> types = RTypeSelector.chooseReportTypes();
        manControl.createAdmin(name, username, password, types);
        PrintHelper.printSuccess(" New admin was created successfully! ");
    }
}
