package ir.ac.kntu.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.ac.kntu.models.Seller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SellerDatabase {
    private static final String FILE_PATH = "database/sellers.json";

    public static ArrayList<Seller> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Seller>>(){}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void save(ArrayList<Seller> sellers) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(sellers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
