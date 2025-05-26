package ir.ac.kntu.services;

import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.models.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustAuthSer extends AuthService {

    private CustomerDB customerDB;

    public CustAuthSer(CustomerDB customerDB) {
        super();
        this.customerDB = customerDB;
    }

    public boolean isValidInput(String firstName, String lastName, String password, String email, String phoneNumber) {
        return isValidName(firstName) && isValidName(lastName) && isValidPassword(password) &&
                isValidEmail(email) && isValidPhoneNumber(phoneNumber);
    }

    public Customer loginByEmail(Map<String, String> info) {
        String email = info.get("email");
        String password = info.get("password");

        if (isValidEmail(email) && isValidPassword(password)) {
            Customer customer = customerDB.findByEmail(email);
            if (customer != null && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    public Customer loginByPhoneNumber(Map<String, String> info) {
        String phoneNumber = info.get("phone number");
        String password = info.get("password");

        if (isValidPhoneNumber(phoneNumber) && isValidPassword(password)) {
            return customerDB.getCustomers().stream()
                    .filter(c -> c != null && c.getPhoneNumber().equalsIgnoreCase(phoneNumber)
                            && c.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public Customer signUp(Map<String, String> info) {
        String firstName = info.get("firstname");
        String lastName = info.get("lastname");
        String password = info.get("password");
        String email = info.get("email");
        String phoneNumber = info.get("phone number");

        if (isValidInput(firstName, lastName, password, email, phoneNumber)) {
            Customer customer = new Customer(firstName, lastName, email, phoneNumber, password);
            customerDB.addCustomer(customer);
            return customer;
        }
        return null;
    }
}
