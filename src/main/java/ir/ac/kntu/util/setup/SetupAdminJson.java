package ir.ac.kntu.util.setup;

import ir.ac.kntu.models.Admin;
import ir.ac.kntu.data.AdminWrapper;
import ir.ac.kntu.util.loaddb.AdminDatabase;

import java.util.Collections;

public class SetupAdminJson {
    public static void main(String[] args) {
        Admin dummyAdmin = new Admin("John", "Doe", "admin1"); // Adjust constructor if needed

        AdminWrapper wrapper = new AdminWrapper(
                Collections.singletonList(dummyAdmin),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        AdminDatabase.save(wrapper);
        System.out.println("✅ admin.json generated successfully.");
    }
}
