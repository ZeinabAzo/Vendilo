package ir.ac.kntu.util.loaddb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.kntu.models.Manager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerDataBase {
    private static final String FILE_PATH = "database/manager.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static List<Manager> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Manager>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void save(List<Manager> managers) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(managers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
