package ir.ac.kntu.data;

import ir.ac.kntu.models.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {

    private final List<Customer> customers;

    public CustomerDB() {
        this.customers = new ArrayList<>();
    }

    public CustomerDB(List<Customer> customers) {
        this.customers = new ArrayList<>(customers);
    }

    public List<Customer> getCustomers() {
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
        for (Customer c : customers) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
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
