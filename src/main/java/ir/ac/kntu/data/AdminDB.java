package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;

import java.util.List;

public class AdminDB {

    private List<Admin> admins;

    public AdminDB(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public Admin findAdminByUserName(String userName) {
        return admins.stream().filter(a -> a.getlName().equals(userName)).findFirst().orElse(null);
    }
}
