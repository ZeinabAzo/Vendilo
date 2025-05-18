package ir.ac.kntu.services;

import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.ui.Page;

public class CustomerAuthService extends AuthService {

    private CustomerDB customerDB;

    public CustomerAuthService(CustomerDB customerDB) {
        super(customerDB);
    }

    public boolean isValidInput(String fName, String lName, String password, String email,
                                 String phoneNumber){
        return isNameValid(fName) && isNameValid(lName) && isValidPassword(password) &&
                isValidEmail(email) && isValidPhoneNumber(phoneNumber);
    }

    public Customer loginByEmail(String email, String password){
        if(isValidEmail(email) && isValidPassword(password)) {
            return customerDB.getCustomers().stream()
                    .filter(c -> c.getEmail().equalsIgnoreCase(email) && c.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public Customer loginByPhoneNumber(String phoneNumber, String password){
        if(isValidPassword(password) && isValidPhoneNumber(phoneNumber)) {
            return customerDB.getCustomers().stream().filter(
                    c -> c.getPhoneNumber().equalsIgnoreCase(phoneNumber) &&
                            c.getPassword().equals(password)).findFirst().orElse(null);
        }
        return null;
    }

    public Customer signUp(String fName, String lName, String password, String email,
                           String phoneNumber){
        if(isValidInput(fName, lName, password, email, phoneNumber)){
            Customer customer= new Customer(fName, lName, password, email, phoneNumber);
            customerDB.addCustomer(customer);
            return customer;
        }
        return null;
    }
}
