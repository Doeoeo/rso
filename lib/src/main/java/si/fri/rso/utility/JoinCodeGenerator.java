package si.fri.rso.utility;

import java.util.Random;

public class JoinCodeGenerator {
    public static String generateCode() {
        int length = 5;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }

        return code.toString();
    }
}
