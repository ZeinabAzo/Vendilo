package ir.ac.kntu.data;

import ir.ac.kntu.enums.ReportType;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Report;
import ir.ac.kntu.models.AuthRequest;
import ir.ac.kntu.models.Seller;

import java.util.List;
import java.util.Objects;

public class AdminDB {

    private List<Admin> admins;
    private List<Report> reports;

    public AdminDB(AdminWrapper wrapper) {
        this.admins = wrapper.getAdmins();
        this.reports = wrapper.getReports();
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

    public AuthRequest getAuthRequest(Seller seller) {
        return (AuthRequest) reports.stream().filter(report -> report.getReportType()
                == ReportType.AUTHENTICATION && Objects.equals(report.getUserID(), seller.getShopID())).
                findFirst().orElse(null);
    }

    public Admin findAdminByUserName(String userName) {
        return admins.stream().filter(admin -> admin.getlName().equals(userName)).findFirst().orElse(null);
    }
}
