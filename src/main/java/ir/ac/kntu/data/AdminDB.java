package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDB {

    private ArrayList<Admin> admins;

    public AdminDB(ArrayList<Admin> admins){
        this.admins =admins;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public Admin findAdminByUserName(String userName){
        return admins.stream().filter(a -> a.getlName().equals(userName)).findFirst().orElse(null);
    }
}
