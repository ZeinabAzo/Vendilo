package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Report;

import java.util.List;

public class AdminWrapper {
    private List<Admin> admins;
    private List<Report> reports;

    public AdminWrapper() {}

    public AdminWrapper(List<Admin> admins,
                        List<Report> reports) {
        this.admins = admins;
        this.reports = reports;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
