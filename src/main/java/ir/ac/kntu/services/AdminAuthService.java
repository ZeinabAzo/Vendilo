package ir.ac.kntu.services;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.models.Admin;

public class AdminAuthService extends AuthService{

    private AdminDB adminDB;

    public AdminAuthService(AdminDB adminDB){
        super();
        this.adminDB = adminDB;
    }

    public Admin login(String fNAme, String userName, String password){
        if(isValidName(fNAme) && isValidPassword(password) && isValidName(userName)){
            Admin admin=adminDB.findAdminByUserName(userName);
            if(admin != null && admin.getPassword().equals(password)){
                return admin;
            }
        }
        return null;
    }
}