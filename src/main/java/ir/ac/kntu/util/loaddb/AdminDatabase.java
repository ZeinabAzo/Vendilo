package ir.ac.kntu.util.loaddb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.ac.kntu.data.AdminWrapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class AdminDatabase {
    private static final String FILE_PATH = "database/admin.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static AdminWrapper load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, AdminWrapper.class);
        } catch (IOException e) {
            System.out.println("Failed to load admin data from file: " + e.getMessage());
            return new AdminWrapper(); // fallback empty wrapper
        }
    }

    public static void save(AdminWrapper wrapper) {
        if (wrapper == null || wrapper.getAdmins() == null || wrapper.getAdmins().isEmpty()) {
            System.out.println("Warning: Trying to save empty admin data. Skipping save.");
            return;
        }
        System.out.println("Saving admins count: " + wrapper.getAdmins().size());
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(wrapper, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
