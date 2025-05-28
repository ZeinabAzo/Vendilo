package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;
import java.util.List;

public class AdminWrapper {
    private List<Admin> admins;
    private List<String> cusComplaint;
    private List<String> sellerCompliant;
    private List<String> authRequest;

    public AdminWrapper() {}

    public AdminWrapper(List<Admin> admins, List<String> cusComplaint, List<String> sellerCompliant,
                        List<String> authRequest) {
        this.admins = admins;
        this.cusComplaint = cusComplaint;
        this.sellerCompliant = sellerCompliant;
        this.authRequest = authRequest;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public List<String> getCusComplaint() {
        return cusComplaint;
    }

    public List<String> getSellerCompliant() {
        return sellerCompliant;
    }

    public List<String> getAuthRequest() {
        return authRequest;
    }
}
