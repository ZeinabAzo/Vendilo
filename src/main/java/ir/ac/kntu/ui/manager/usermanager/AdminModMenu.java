package ir.ac.kntu.ui.manager.usermanager;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.enums.ReportType;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.RTypeSelector;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.*;

public class AdminModMenu {
    
    private ManControl manControl;
    
    public AdminModMenu(ManControl manControl){
        this.manControl = manControl;
    }

    public void firstPage(Admin admin) {
        PrintHelper.ask("what do you want to modify?");
        PrintHelper.option(1, "change name");
        PrintHelper.option(2, "change username");
        PrintHelper.option(3, "modify accessibility");
        PrintHelper.option(4, "return");
        PrintHelper.option(5, "exit");
        int option = ScannerWrapper.nextInt();

        while (true){
            switch (option){
                case 1 -> changeName(admin);
                case 2 -> changeUsername(admin);
                case 3 -> modifyAccess(admin);
                case 4 -> {
                    return;
                }
                case 5 -> Exit.exit();
                default -> PrintHelper.printError("TRY AGAIN");
            }
        }
    }

    private void modifyAccess(Admin admin) {
        ReportType[] allTypes = ReportType.values();
        List<ReportType> accesses = manControl.getAdmin(admin).getAccesses();

        Map<Integer, ReportType> accessed = new HashMap<>();
        Map<Integer, ReportType> notAccessed = new HashMap<>();

        PrintHelper.printInfo("Currently available:");
        int index = getIndex(accesses, accessed, allTypes, notAccessed);

        PrintHelper.ask("Choose an option. Out-of-range ends the process. Selecting an available type " +
                "removes it; selecting an unavailable type adds it.");

        while (true) {
            int option = ScannerWrapper.nextInt();

            if (option < 0 || option >= index) {
                break;
            }

            if (accessed.containsKey(option)) {
                ReportType toRemove = accessed.get(option);
                accesses.remove(toRemove);
                PrintHelper.printInfo(toRemove + " removed.");
            } else if (notAccessed.containsKey(option)) {
                ReportType toAdd = notAccessed.get(option);
                accesses.add(toAdd);
                PrintHelper.printInfo(toAdd + " added.");
            }
        }

        manControl.getAdmin(admin).setAccesses(accesses);
    }

    private static int getIndex(List<ReportType> accesses, Map<Integer, ReportType> accessed, ReportType[] allTypes, Map<Integer, ReportType> notAccessed) {
        int index = 0;
        for (ReportType reportType : accesses) {
            PrintHelper.option(index, RTypeSelector.formatEnumName(reportType));
            accessed.put(index, reportType);
            index++;
        }

        PrintHelper.printInfo("Not available for this admin:");
        for (ReportType reportType : allTypes) {
            if (!accesses.contains(reportType)) {
                PrintHelper.option(index, RTypeSelector.formatEnumName(reportType));
                notAccessed.put(index, reportType);
                index++;
            }
        }
        return index;
    }


    private void changeUsername(Admin admin) {
        PrintHelper.ask("What is the new username going to be?");
        String name = ScannerWrapper.nextLine();
        if (name.matches("[a-zA-Z ]+") && manControl.getAdmin(admin) != null){
            manControl.getAdmin(admin).setlName(name);
        }else{
            PrintHelper.printError("Invalid name or null admin");
        }
    }

    private void changeName(Admin admin) {
        PrintHelper.ask("What is the new name going to be?");
        String name = ScannerWrapper.nextLine();
        if (name.matches("[a-zA-Z ]+") && manControl.getAdmin(admin) != null){
            manControl.getAdmin(admin).setfName(name);
        }else{
            PrintHelper.printError("Invalid name or null admin");
        }
    }
}
