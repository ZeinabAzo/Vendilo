package ir.ac.kntu.util;

import com.google.gson.*;
import ir.ac.kntu.models.*;

import java.lang.reflect.Type;

public class ProductAdapter implements JsonDeserializer<Product>, JsonSerializer<Product> {

    @Override
    public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        String type = obj.get("type").getAsString();

        return switch (type) {
            case "book" -> context.deserialize(obj, Book.class);
            case "mobile" -> context.deserialize(obj, Mobile.class);
            case "laptop" -> context.deserialize(obj, Laptop.class);
            default -> throw new JsonParseException("Unknown product type: " + type);
        };
    }

    @Override
    public JsonElement serialize(Product product, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(product, product.getClass());
    }
}
