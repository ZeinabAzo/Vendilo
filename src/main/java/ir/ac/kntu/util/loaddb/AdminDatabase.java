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

    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static AdminWrapper load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            return gson.fromJson(reader, AdminWrapper.class);
        } catch (IOException e) {
            return new AdminWrapper(); // returns empty object
        }
    }

    public static void save(AdminWrapper wrapper) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(wrapper, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
