package ir.ac.kntu.ui.cusmenu;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.models.DiscountNotif;
import ir.ac.kntu.models.Notification;
import ir.ac.kntu.models.StockRefill;
import ir.ac.kntu.util.PrintHelper;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.ShowProductInfo;
import ir.ac.kntu.util.SplitDisplay;

import java.util.List;

public class NotifMenu {
    private CusControl cusControl;

    public NotifMenu(CusControl cusControl){
        this.cusControl = cusControl;
    }

    public void showPage(){
        PrintHelper.miniUpperBorder(" WELCOME TO NOTIFICATION-CENTER! ");
        PrintHelper.ask("Please choose one notification to proceed: ");
        List<Notification> notifications = cusControl.getCustomer().getNotifications();
        int choice = SplitDisplay.show(notifications);
        Notification notification = notifications.get(choice);
        notificationSpecifics(notification);
    }

    private void notificationSpecifics(Notification notification) {
        PrintHelper.ask("Do you want to see details of this notification?(yes/no) ");
        String answer = ScannerWrapper.nextLine().trim().toLowerCase();
        if(answer.equals("yes")){
            detectNotifType(notification);
        }
    }

    private void detectNotifType(Notification notification) {
        if(notification instanceof DiscountNotif discountNotif){
            System.out.println(discountNotif.getDiscount());
            PrintHelper.printSuccess("This is your discount, use it wisely!");
        } else if ((notification instanceof StockRefill stockRefill) && cusControl.getCustomer().hasVendiloPlus()) {
            ShowProductInfo.showProduct(stockRefill.getProduct());
            PrintHelper.ask("Do you want to add this product to your cart?");
            cusControl.orderProduct(stockRefill.getProduct());
        }else {

        }
    }
}
