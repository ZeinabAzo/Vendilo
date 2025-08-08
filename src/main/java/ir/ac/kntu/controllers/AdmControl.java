package ir.ac.kntu.controllers;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.data.CustomerDB;
import ir.ac.kntu.enums.ReportType;
import ir.ac.kntu.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdmControl {

    private AdminDB adminDB;
    private Admin admin;
    private CustomerDB customerDB;

    public AdmControl(AdminDB adminDB, CustomerDB customerDB, Admin admin) {
        this.adminDB = adminDB;
        this.customerDB = customerDB;
        this.admin = admin;
    }

    public AdminDB getAdminDB() {
        return adminDB;
    }





    public Admin getAdmin() {
        return admin;
    }


    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        for (Customer c : customerDB.getCustomers()) {
            orders.addAll(c.getPurchOrders());
        }

        return orders;
    }

    public List<Order> getByEmail(String email) {
        List<Order> orders = new ArrayList<>();

        for (Customer c : customerDB.getCustomers()) {
            if (email.equals(c.getEmail())) {
                orders.addAll(c.getPurchOrders());
                break;
            }
        }

        return orders;
    }

    public List<Order> getByDate(LocalDate start, LocalDate end) {
        List<Order> orders = getAllOrders();

        orders.removeIf(o -> o.getOrderDate().isBefore(start) || o.getOrderDate().isAfter(end));

        return orders;
    }

    public List<Report> getReports(ReportType selected) {
        return adminDB.getReports().stream().filter(report -> !report.isResponseStatus() &&
                report.getReportType()==selected).toList();
    }
}