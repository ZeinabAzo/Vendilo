package ir.ac.kntu.util.loaddb;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ir.ac.kntu.models.Product;
import ir.ac.kntu.models.Seller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SellerDatabase {
    private static final String FILE_PATH = "database/seller.json";

    // Register LocalDate adapter to avoid reflection error
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(Product.class, new ProductAdapter())
            .setPrettyPrinting()
            .create();

    public static ArrayList<Seller> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Seller>>() {}.getType();
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

    // LocalDate Adapter Inner Class
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.toString()); // ISO format
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString());
        }
    }
}
