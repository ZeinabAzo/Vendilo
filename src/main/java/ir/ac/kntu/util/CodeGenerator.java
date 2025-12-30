package ir.ac.kntu.util;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class CodeGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 5;
    private static final SecureRandom random = new SecureRandom();
    private static final Set<String> usedCodes = new HashSet<>(); // for uniqueness

    public static String generateUniqueCode() {
        String code;
        do {
            code = "SAVE-" + getRandomCode();
        } while (usedCodes.contains(code));

        usedCodes.add(code);
        return code;
    }

    private static String getRandomCode() {
        StringBuilder builder = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            builder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return builder.toString();
    }
}

