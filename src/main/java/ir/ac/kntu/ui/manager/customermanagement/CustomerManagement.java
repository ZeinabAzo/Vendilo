package ir.ac.kntu.ui.manager.customermanagement;

import ir.ac.kntu.controllers.ManControl;
import ir.ac.kntu.enums.DiscountType;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Discount;
import ir.ac.kntu.models.User;
import ir.ac.kntu.util.Exit;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.SplitDisplay;

import java.util.List;

public class CustomerManagement {

    private ManControl manControl;

    public CustomerManagement(ManControl manControl) {
        this.manControl = manControl;
    }

    public void firstPage() {
        PrintHelper.printInfo("You gotta choose one customer to continue:");
        List<User> users = customerFilter();
        int chosen = SplitDisplay.show(users);
        if(users==null){
            return;
        }
        Customer customer = (Customer) users.get(chosen);
        secondPage(customer);
    }

    private void secondPage(Customer customer) {
        PrintHelper.printInfo("Choose what you want to do here: ");
        PrintHelper.option(1,"check customer's last month activities");
        PrintHelper.option(2,"gift him/her a discount code");
        PrintHelper.option(3,"make him/her a vendilo+ user");
        PrintHelper.option(4, "return");
        PrintHelper.option(5, "exit");
        int chosen = ScannerWrapper.nextInt();

        while (true){
            switch (chosen){
                case 1 -> activityCheck(customer);
                case 2 -> giftDiscount(customer);
                case 3 -> vendiloUserGift(customer);
                case 4 -> {
                    return;
                }
                case 5 -> Exit.exit();
                default -> PrintHelper.printError("Wrong input");
            }
        }
    }

    private void vendiloUserGift(Customer customer) {
        customer.setVendiloPlus(true);
        PrintHelper.printSuccess("this customer is now a vendilo+ user");
    }

    private void giftDiscount(Customer customer) {
        PrintHelper.ask("What kind of discount are you planning to gift?");
        PrintHelper.option(1, "General");
        PrintHelper.option(2,"Special");
        PrintHelper.option(3, "return");
        int chosen = ScannerWrapper.nextInt();
        DiscountType discountType = null;
        switch (chosen){
            case 1 -> discountType = DiscountType.GENERAL;
            case 2 -> discountType = DiscountType.SPECIAL;
            case 3 -> {
                return;
            }
            default -> PrintHelper.printError("Wrong input");
        }
        PrintHelper.ask("How much would it worth?");
        double value = ScannerWrapper.nextDouble();
        PrintHelper.ask("And for how many times can they use it?");
        int maxUsage = ScannerWrapper.nextInt();
        Discount discount = new Discount(discountType, value, maxUsage);
        PrintHelper.ask("What would your massage be?");
        String massage = ScannerWrapper.nextLine();
        manControl.giftDiscount(customer, discount, massage);
        PrintHelper.printSuccess("Notification sent successfully");
    }

    private void activityCheck(Customer customer) {
        double amountSpent = manControl.cusActivityCheck(customer);
        PrintHelper.printInfo(String.format("This customer has spent $%.2f in the last month", amountSpent));
    }

    private List<User> customerFilter() {
        PrintHelper.ask("Choose how you want to proceed: ");
        PrintHelper.option(1, "filter by email");
        PrintHelper.option(2, "filter by name");
        PrintHelper.option(3, "no filter");
        PrintHelper.option(4, "return");
        int option = ScannerWrapper.nextInt();

        switch (option){
            case 1 -> {
                PrintHelper.ask("Enter the specific email");
                String email = ScannerWrapper.nextLine();
                return manControl.filterCusEmail(email);
            }
            case 2 -> {
                PrintHelper.ask("Enter the name, be gentle we'll find them");
                String name = ScannerWrapper.nextLine();
                PrintHelper.printInfo("We will show you up to 75% similar names-" +
                        "whether its first or last or full name");
                return manControl.filterCusName(name);
            }
            case 3 -> {
                return manControl.getAllCus();
            }
            case 4 ->{
                return null;
            }
            default -> PrintHelper.printError("wrong choice");
        }
        return null;
    }
}
