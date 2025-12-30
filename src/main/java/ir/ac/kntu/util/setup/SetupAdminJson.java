package ir.ac.kntu.util.setup;

import ir.ac.kntu.data.AdminWrapper;
import ir.ac.kntu.util.loaddb.AdminDatabase;

import java.util.Collections;

public class SetupAdminJson {
    public static void main(String[] args) {

        AdminWrapper wrapper = new AdminWrapper(
                Collections.emptyList(),
                Collections.emptyList()
        );

        AdminDatabase.save(wrapper);
        System.out.println("✅ admin.json generated successfully.");
    }
}
