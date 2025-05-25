package ir.ac.kntu.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ir.ac.kntu.models.Customer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CustomerDatabase {
    private static final String FILE_PATH = "database/customers.json";

    public static ArrayList<Customer> load() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Customer>>(){}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void save(ArrayList<Customer> customers) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(customers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
