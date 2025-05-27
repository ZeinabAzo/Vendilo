package ir.ac.kntu.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SellerDatabase {
    private static final String FILE_PATH = "database/seller.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Product.class, new ProductAdapter())
            .setPrettyPrinting()
            .create();


    public static ArrayList<Seller> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Seller>>() {
            }.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void save(List<Seller> sellers) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(sellers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
