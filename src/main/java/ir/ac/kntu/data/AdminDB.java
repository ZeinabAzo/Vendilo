package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Complaint;
import ir.ac.kntu.models.AuthRequest;

import java.util.List;

public class AdminDB {

    private List<Admin> admins;
    private List<Complaint> cusComplaint;
    private List<Complaint> sellerCompliant;
    private List<AuthRequest> authRequest;

    public AdminDB(AdminWrapper wrapper) {
        this.admins = wrapper.getAdmins();
        this.cusComplaint = wrapper.getCusComplaint();
        this.sellerCompliant = wrapper.getSellerCompliant();
        this.authRequest = wrapper.getAuthRequest();
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Complaint> getCusComplaint() {
        return cusComplaint;
    }

    public List<Complaint> addCusComplaint(Complaint complaint){
        cusComplaint.add(complaint);
    }

    public List<Complaint> getSellerCompliant() {
        return sellerCompliant;
    }

    public List<AuthRequest> getAuthRequest() {
        return authRequest;
    }

    public Admin findAdminByUserName(String userName) {
        return admins.stream()
                .filter(a -> a.getlName().equals(userName))
                .findFirst()
                .orElse(null);
    }
}
