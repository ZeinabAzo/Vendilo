package ir.ac.kntu.services;

import static ir.ac.kntu.util.PrintHelper.printError;

public class AuthService {


    public static boolean isValidName(String name) {
        if (name.matches("([a-zA-Z]+\\s*)*")) {
            return true;
        } else {
            printError("Invalid name format");
            return false;
        }
    }

    public static boolean isValidPassword(String password) {
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#_\\-]).{8,15}$")) {
            return true;
        } else {
            printError("""
                    Invalid password. Password must contain at least:
                    one lowercase letter (a-z)
                    one uppercase letter (A-Z)
                    one digit (0-9)
                    one special character (@, #, _, or -)
                    and be between 8 to 15 characters long.""");
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        if (email.matches("^[\\w._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return true;
        } else {
            printError("Invalid email format.");
            return false;
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[0-9]{10,11}")) {
            return true;
        } else {
            printError("Invalid phone number. It must contain 10 or 11 digits.");
            return false;
        }
    }

    public static boolean isValidID(String nationId) {
        if (nationId.matches("[0-9]{10}")) {
            return true;
        } else {
            printError("Invalid id. It must contain 10 digits.");
            return false;
        }
    }

    public static boolean isValidSellerAddress(String state) {
        return state.matches("([a-zA-Z]+\\s*)*");
    }

}