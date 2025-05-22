package ir.ac.kntu.services;

import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.models.Customer;

public class CustomerAuthService extends AuthService {

    private CustomerDB customerDB;

    public CustomerAuthService(CustomerDB customerDB) {
        super();
        this.customerDB=customerDB;
    }

    public boolean isValidInput(String[] name, String password, String email, String phoneNumber){
        return isValidName(name[1]) && isValidName(name[2]) && isValidPassword(password) &&
                isValidEmail(email) && isValidPhoneNumber(phoneNumber);
    }

    public Customer loginByEmail(String email, String password){
        if(isValidEmail(email) && isValidPassword(password)) {
            Customer customer= customerDB.findByEmail(email);
            if(customer != null && customer.getPassword().equals(password)){
                return customer;
            }
        }
        return null;
    }

    public Customer loginByPhoneNumber(String phoneNumber, String password){
        if(isValidPassword(password) && isValidPhoneNumber(phoneNumber)) {
            return customerDB.getCustomers().stream().filter(
                    c -> c != null && c.getPhoneNumber().equalsIgnoreCase(phoneNumber) &&
                            c.getPassword().equals(password)).findFirst().orElse(null);
        }
        return null;
    }

    public Customer signUp(String[] name, String password, String email, String phoneNumber){
        if(isValidInput(name, password, email, phoneNumber)){
            Customer customer= new Customer(name[1], name[2], password, email, phoneNumber);
            customerDB.addCustomer(customer);
            return customer;
        }
        return null;
    }
}
