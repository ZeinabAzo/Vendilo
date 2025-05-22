package ir.ac.kntu.services;

import ir.ac.kntu.ui.Page;

public class AuthService {

    public AuthService() {
    }

    public String[] attachNames(String fName, String lName){
        return new String[]{fName, lName};
    }

    public boolean isValidName(String name){
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

    public boolean isValidID(String ID){
        if (ID.matches("[0-9]{10}")) {
            return true;
        } else {
            Page.printError("Invalid phone number. It must contain 10 digits.");
            return false;
        }
    }

    public boolean isValidCustomerAddress(String title, String state, String city){
        return title.matches("([a-zA-Z]+\\s*)*") && state.matches("([a-zA-Z]+\\s*)*") &&
                city.matches("([a-zA-Z]+\\s*)*");
    }

    public boolean isValidSellerAddress(String state){
        return state.matches("([a-zA-Z]+\\s*)*");
    }

}