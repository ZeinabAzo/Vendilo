package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;
import ir.ac.kntu.models.Complaint;
import ir.ac.kntu.models.AuthRequest;
import ir.ac.kntu.models.Seller;

import java.util.List;
import java.util.Objects;

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

    public Complaint findSellComp(int index){
        return sellerCompliant.get(index);
    }

    public List<Complaint> getCusComplaint() {
        return cusComplaint;
    }

    public void addCusComplaint(Complaint complaint){
        cusComplaint.add(complaint);
    }

    public List<Complaint> getSellerCompliant() {
        return sellerCompliant;
    }

    public List<AuthRequest> getAuthRequest() {
        return authRequest;
    }

    public AuthRequest getAuthRequest(Seller seller){
        return authRequest.stream().filter(r -> (Objects.equals(r.getSeller().getShopID(),
                seller.getShopID()))).findAny().orElse(null);
    }

    public Admin findAdminByUserName(String userName) {
        return admins.stream()
                .filter(a -> a.getlName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public void addAdmin(Admin admin){
        admins.add(admin);
    }

    public Complaint findCusComp(int index) {
        return cusComplaint.get(index);
    }
}
