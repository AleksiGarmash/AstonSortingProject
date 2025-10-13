package Model;
import java.util.Random;

public class DataGenerator {
    private static final Random random = new Random();
    private static final String CAPITAL_LETTERS = "ADCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SMALL_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String[] DOMAINS = {"gmail.com", "yandex.ru", "outlook.com", "hotmail.com"};
    private static final String CHARS_FOR_EMAIL = "abcdefghijklmnopqrstuvwxyz1234567890";

    public static String generateRandomString() {
        int length = random.nextInt(11) + 5; // длина от 5 до 15
        StringBuilder sb = new StringBuilder(length);
        sb.append(CAPITAL_LETTERS.charAt(random.nextInt(CAPITAL_LETTERS.length())));
        for (int i = 0; i < length-1; i++) {
            int index = random.nextInt(SMALL_LETTERS.length());
            sb.append(SMALL_LETTERS.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomEmail(){
        int localPartLength = random.nextInt(8) + 5;
        StringBuilder localPart = new StringBuilder(localPartLength);
        for (int i = 0; i < localPartLength; i++) {
            int index = random.nextInt(CHARS_FOR_EMAIL.length());
            localPart.append(CHARS_FOR_EMAIL.charAt(index));
        }
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        return localPart + "@" + domain;
    }
}