package ir.ac.kntu.util.loaddb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.kntu.models.Admin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AdminDatabase {
    private static final String FILE_PATH = "database/admin.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static List<Admin> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Admin>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void save(List<Admin> admins) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(admins, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
