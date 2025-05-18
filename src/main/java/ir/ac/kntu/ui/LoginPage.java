package ir.ac.kntu.ui;

import java.util.ArrayList;

public class LoginPage extends Page{

    private Page userMenu;

    public LoginPage(Page previousPage, Page userMenu){
        super(previousPage);
        this.userMenu=userMenu;
    }
}
