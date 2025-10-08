package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final Random random = new Random();
    private static final String CAPITAL_LETTERS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯ";
    private static final String SMALL_LETTERS = "абвгдежзийклмнопрстуфхцчшщыьэюя";

    public static List<Person> generateRandomPeople(int count) {
        List<Person> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(
                    new Person.PersonBuilder()
                            .withName(generateRandomString())
                            .withAge(random.nextInt(80) + 18)
                            .build()
            );
        }
        return result;
    }

    /**
     * Генерирует случайную строку длины от 5 до 15 символов,
     * состоящую только из английских букв.
     */
    private static String generateRandomString() {
        int length = random.nextInt(11) + 5; // длина от 5 до 15
        StringBuilder sb = new StringBuilder(length);
        sb.append(CAPITAL_LETTERS.charAt(random.nextInt(CAPITAL_LETTERS.length())));
        for (int i = 0; i < length-1; i++) {
            int index = random.nextInt(SMALL_LETTERS.length());
            sb.append(SMALL_LETTERS.charAt(index));
        }
        return sb.toString();
    }
}