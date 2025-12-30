package ir.ac.kntu.services.authentication;

import ir.ac.kntu.data.ManagerDB;
import ir.ac.kntu.models.Manager;

import java.util.Map;

public class ManagerAuthSer extends AuthService{
    private ManagerDB managerDB;

    public ManagerAuthSer(ManagerDB managerDB) {
        super();
        this.managerDB = managerDB;
    }

    public Manager login(Map<String, String> info){
        String fNAme = info.get("name");
        String userName = info.get("username");
        String password = info.get("password");

        if (isValidName(attachNames(fNAme, userName)) && isValidPassword(password) ) {
            Manager manager = managerDB.findByUsername(userName);
            if (manager != null && manager.getPassword().equals(password)) {
                return manager;
            }
        }
        return null;
    }
}
