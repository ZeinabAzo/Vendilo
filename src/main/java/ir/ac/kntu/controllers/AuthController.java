package ir.ac.kntu.controllers;

import ir.ac.kntu.models.Admin;
import ir.ac.kntu.services.AdminAuthService;

public class AuthController {

    public Admin loginAsAdmin(String fullName, String userName, String password, AdminAuthService authService) {
        if (fullName == null || userName == null || password == null) {
            return null;
        }

        return authService.login(fullName, userName, password);
    }
}
