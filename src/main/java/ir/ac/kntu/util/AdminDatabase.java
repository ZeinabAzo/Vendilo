package ir.ac.kntu.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.ac.kntu.models.Admin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AdminDatabase {
    private static final String FILE_PATH = "database/admins.json";

    public static ArrayList<Admin> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Admin>>(){}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void save(ArrayList<Admin> admins) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(admins, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
