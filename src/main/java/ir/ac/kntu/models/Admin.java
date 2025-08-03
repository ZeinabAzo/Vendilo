package ir.ac.kntu.models;

public class Admin extends User {

    public Admin(String fName, String userName, String password) {
        super(fName, userName, password);
        this.setActive(true);
    }

    //override to string
}
