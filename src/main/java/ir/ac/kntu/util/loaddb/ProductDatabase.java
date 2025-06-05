package ir.ac.kntu.util.loaddb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ir.ac.kntu.models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static final String FILE_PATH = "database/product.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static List<Product> load() {
        List<Product> products = new ArrayList<>();

        try (FileReader reader = new FileReader(FILE_PATH)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                String type = element.getAsJsonObject().get("type").getAsString();

                Product product = null;

                switch (type) {
                    case "book" -> product = gson.fromJson(element, Book.class);
                    case "laptop" -> product = gson.fromJson(element, Laptop.class);
                    case "mobile" -> product = gson.fromJson(element, Mobile.class);
                    default -> System.out.println("⚠️ Unknown product type: " + type);
                }

                if (product != null) {
                    if (product.getRatedUserIds() == null) {
                        product.setRatedUserIds(new java.util.HashSet<>());
                    }
                    products.add(product);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static void save(List<Product> products) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
