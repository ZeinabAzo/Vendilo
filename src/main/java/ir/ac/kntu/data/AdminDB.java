package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;

import java.util.List;

public class AdminDB {

    private List<Admin> admins;
    private List<String> cusComplaint;
    private List<String> sellerCompliant;
    private List<String> authRequest;

    public AdminDB(AdminWrapper wrapper) {
        this.admins = wrapper.getAdmins();
        this.cusComplaint = wrapper.getCusComplaint();
        this.sellerCompliant = wrapper.getSellerCompliant();
        this.authRequest = wrapper.getAuthRequest();
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<String> getCusComplaint() {
        return cusComplaint;
    }

    public void setCusComplaint(List<String> cusComplaint) {
        this.cusComplaint = cusComplaint;
    }

    public List<String> getSellerCompliant() {
        return sellerCompliant;
    }

    public void setSellerCompliant(List<String> sellerCompliant) {
        this.sellerCompliant = sellerCompliant;
    }

    public List<String> getAuthRequest() {
        return authRequest;
    }

    public void setAuthRequest(List<String> authRequest) {
        this.authRequest = authRequest;
    }


    // other methods...
}
