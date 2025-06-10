package ir.ac.kntu.services.authentication;

import ir.ac.kntu.data.AdminDB;
import ir.ac.kntu.models.Admin;

import java.util.Map;

public class AdminAuthSer extends AuthService {

    private AdminDB adminDB;

    public AdminAuthSer(AdminDB adminDB) {
        super();
        this.adminDB = adminDB;
    }

    public Admin login(Map<String, String> info) {

        String fNAme = info.get("name");
        String userName = info.get("username");
        String password = info.get("password");

        if (isValidName(attachNames(fNAme, userName)) && isValidPassword(password) ) {
            Admin admin = adminDB.findAdminByUserName(userName);
            if (admin != null && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }
}