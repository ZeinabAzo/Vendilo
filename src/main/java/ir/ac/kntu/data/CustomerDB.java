package ir.ac.kntu.data;

import ir.ac.kntu.models.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {

    private ArrayList<Customer> customers;

    public CustomerDB() {
        this.customers = new ArrayList<>();
    }

    public CustomerDB(List<Customer> customers) {
        this.customers = new ArrayList<>(customers);
    }

    public ArrayList<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
        }
    }

    public int size() {
        return customers.size();
    }

    public Customer findByEmail(String email) {
        return customers.stream().filter( c -> c.getEmail().equals(email)).findFirst().orElse(null);

    }

    public Customer getCustomer(Customer customer){
        return customers.stream().filter(c -> c.equals(customer)).findFirst().orElse(null);
    }

    public boolean updateCustomer(Customer updated) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equals(updated.getEmail())) {
                customers.set(i, updated);
                return true;
            }
        }
        return false;
    }


}
