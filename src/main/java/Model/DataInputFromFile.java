package Model;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataInputFromFile {
    public static List<Person> loadFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        List<Person> result = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                try {
                    result.add(
                            new Person.PersonBuilder()
                                    .withName(parts[0].trim())
                                    .withAge(Integer.parseInt(parts[1].trim()))
                                    .build()
                    );
                } catch (RuntimeException e) {
                    System.err.println("Ошибка в строке \"" + line + "\": " + e.getMessage());
                }
            } else {
                System.err.println("Неправильный формат строки: " + line);
            }
        }
        return result;
    }


    public static List<Person> generateRandomly(int size) {
        return DataGenerator.generateRandomPeople(size);
    }

    public static List<Person> enterManually(Scanner scanner) {
        List<Person> result = new ArrayList<>();
        System.out.println("Введите число записей: ");
        int numRecords = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        for (int i = 0; i < numRecords; i++) {
            System.out.println("Запись №" + (i + 1) + ":");
            System.out.print("Имя: ");
            String name = scanner.nextLine();
            System.out.print("Возраст: ");
            int age = 18;
            try{
                age = scanner.nextInt();
            } catch (RuntimeException e) {
                System.err.println("Введите число.");
            }
            scanner.nextLine();

            try {
                result.add(new Person.PersonBuilder().withName(name).withAge(age).build());
            } catch (IllegalArgumentException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return result;
    }
}