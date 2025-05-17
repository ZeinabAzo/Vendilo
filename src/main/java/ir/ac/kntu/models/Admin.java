package ir.ac.kntu.models;

public class Admin extends User{

    //some data I don't know yet

    public Admin(String fName, String userName, String password){
        super(fName, userName);
        this.password=password;
    }
}
