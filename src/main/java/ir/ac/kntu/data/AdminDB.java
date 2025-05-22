package ir.ac.kntu.data;

import ir.ac.kntu.models.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDB {

    private List<Admin> admins;
    private Admin admin1=new Admin("jdl", "dewj8", "ewj");
    private Admin admin2=new Admin("idkh", "kjxwa", "dkj");

    public AdminDB(){
        this.admins = new ArrayList<>(List.of(admin1, admin2));
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public Admin findAdminByUserName(String userName){
        return admins.stream().filter(a -> a.getlName().equals(userName)).findFirst().orElse(null);
    }
}
