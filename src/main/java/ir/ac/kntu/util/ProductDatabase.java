package ir.ac.kntu.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.kntu.models.Product;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProductDatabase {
    private static final String FILE_PATH = "database/products.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static ArrayList<Product> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void save(ArrayList<Product> products) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
