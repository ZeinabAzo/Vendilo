package ir.ac.kntu.services;

import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.ui.Page;

public class AuthService {

    private CustomerDB customerDB;

    public AuthService(CustomerDB customerDB) {
        this.customerDB = customerDB;
    }

    public boolean isNameValid(String name){
        if(name.matches("([a-zA-Z]+\\s*)*")){
            return true;
        }else{
            Page.printError("Invalid name format");
            return false;
        }
    }

    public boolean isValidPassword(String password){
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#_\\-]).{8,15}$")){
            return true;
        }else{
            Page.printError("""
                    Invalid password. Password must contain at least:
                    one lowercase letter (a-z)
                    one uppercase letter (A-Z)
                    one digit (0-9)
                    one special character (@, #, _, or -)
                    and be between 8 to 15 characters long.""");
            return false;
        }
    }

    public boolean isValidEmail(String email) {
        if (email.matches("^[\\w._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return true;
        } else {
            Page.printError("Invalid email format.");
            return false;
        }
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]{10,11}")) {
            return true;
        } else {
            Page.printError("Invalid phone number. It must contain 10 or 11 digits.");
            return false;
        }
    }
}
