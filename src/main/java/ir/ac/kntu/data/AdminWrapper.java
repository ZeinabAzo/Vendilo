package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.AuthRequest;
import ir.ac.kntu.models.Complaint;

import java.util.List;

public class AdminWrapper {
    private List<Admin> admins;
    private List<Complaint> cusComplaint;
    private List<Complaint> sellerCompliant;
    private List<AuthRequest> authRequest;

    public AdminWrapper() {}

    public AdminWrapper(List<Admin> admins,
                        List<Complaint> cusComplaint,
                        List<Complaint> sellerCompliant,
                        List<AuthRequest> authRequest) {
        this.admins = admins;
        this.cusComplaint = cusComplaint;
        this.sellerCompliant = sellerCompliant;
        this.authRequest = authRequest;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<Complaint> getCusComplaint() {
        return cusComplaint;
    }

    public List<Complaint> getSellerCompliant() {
        return sellerCompliant;
    }

    public List<AuthRequest> getAuthRequest() {
        return authRequest;
    }
}
