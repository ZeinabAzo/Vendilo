package ir.ac.kntu.ui;

public class SignUp extends Page{

    private Page userMenu;

    public SignUp(Page previousPage, Page userMenu){
        super(previousPage);
        this.userMenu=userMenu;
    }
}
