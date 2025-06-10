package ir.ac.kntu.ui.cusmenu;

import ir.ac.kntu.controllers.CusControl;
import ir.ac.kntu.models.Discount;
import ir.ac.kntu.util.PrintHelper;

public class CusDisMen {

    private CusControl cusControl;

    public CusDisMen(CusControl cusControl){
        this.cusControl = cusControl;
    }

    public void showPage(){
        PrintHelper.miniUpperBorder("CHOOSE ONE DISCOUNT TO SEE DETAILS");
        Discount discount = cusControl.showDiscounts();
        PrintHelper.miniLowerBorder("CHOOSE ONE DISCOUNT TO SEE DETAILS");

        PrintHelper.printInfo(discount.toString());
        PrintHelper.printInfo("DISCOUNT INFO: (copy paste if you need)");
        System.out.println(discount.showALL());
    }
}
