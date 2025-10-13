package App;

import Collection.CustomList;
import Model.DataGenerator;
import Model.Person;
import Search.BinarySearch;
import Util.FileUtil;
import Util.Validation;

import java.io.IOException;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {

    /* TODO: Задание Е (меню, логика работы, интеграция всех частей)
        Реализовать:
        1. Возможность выбирать варианты заполнения исходного массива данных (из файла, рандом, вручную) и его длину
        2. Возможность найти какой-либо элемент отсортированной коллекции при помощи алгоритма бинарного поиска
     */


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        CustomList<Person> list = new CustomList<>();
        System.out.println("=== Приложение сортировки данных кастомных классов ===");

        while (true) {
            System.out.println(
                    "\nМеню: \n" +
                            "1. Заполнение данных вручную\n" +
                            "2. Заполнение данных рандомно\n" +
                            "3. Заполнение данных из файла\n" +
                            "4. Сортировка ... \n" +
                            "5. Сортировка ... \n" +
                            "6. Бинарный поиск\n" +
                            "== Дополнительное ==\n" +
                            "7. Сортировка ... \n" + // чет\нечет
                            "8. Запись данных в файл\n" +
                            "9. Подсчет вхождений (многопоточно)\n" +
                            "0. Выход\n" +
                            "\nВыбор: "

            );

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> fillManual(list);
                case "2" -> fillRandom(list);
                case "3" -> fillFromFile(list);
                case "4" -> runSort();
                case "5" -> runSort();
                case "6" -> runBinarySearch(list);
                case "7" -> runEvenFieldSort();
                case "8" -> appendToFile();
                case "9" -> countOccurrences();
                case "0" -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный ввод");
            }
        }
    }

    private static void fillManual(CustomList<Person> list) {
        list.clear();

        System.out.println("Введите количество записей: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Запись №" + (i + 1) + ":");

            System.out.print("Имя: ");
            String name = scanner.nextLine();

            System.out.print("Возраст: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Электронная почта: ");
            String email = scanner.nextLine();

            try {
                Validation.validatePerson(name, age, email);
                list.add(new Person.Builder().withName(name).withAge(age).withEmail(email).build());
            } catch (Exception e) {
                System.out.println("Ошибка ввода! Повторите.");
                i--;
            }
        }


    }

    private static void fillRandom(CustomList<Person> list) {
        list.clear();
        Random random = new Random();
        DataGenerator generator = new DataGenerator();

        System.out.println("Введите количество записей: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            list.add(
                    new Person.Builder()
                            .withName(generator.generateRandomString())
                            .withAge(random.nextInt(80) + 18)
                            .withEmail(generator.generateRandomEmail())
                            .build()
            );
        }
    }

    private static void fillFromFile(CustomList<Person> list) throws IOException {
        list.clear();
        System.out.println("Файл: ");
        String path = scanner.nextLine();

        FileUtil.readFile(path).forEach(line -> {
            String[] parts = line.split(", ");
            if (parts.length == 3) {
                String name = parts[0].trim();
                int age = Integer.parseInt(parts[1].trim());
                String email = parts[2].trim();

                try {
                    Validation.validatePerson(name, age, email);
                    list.add(
                            new Person.Builder()
                                    .withName(name)
                                    .withAge(age)
                                    .withEmail(email)
                                    .build()
                    );
                } catch (RuntimeException e) {
                    System.err.println("Ошибка в строке \"" + line + "\": " + e.getMessage());
                }
            } else {
                System.err.println("Неправильный формат строки: " + line);
            }
        });

    }

    private static <T> void runSort() {

    }

    private static void runBinarySearch(CustomList<Person> list) {

        if (list.isEmpty()) {
            System.out.println("Пусто");
            return;
        }

        System.out.print("Имя для поиска: ");
        String key = scanner.nextLine();

        int index = BinarySearch.binarySearch(
                list,
                new Person.Builder().withName(key).withAge(0).build(),
                Comparator.comparing(Person::getName));

        System.out.println(index >= 0 ? "Найдено: " + list.get(index) : "Не найдено");
    }

    private static void runEvenFieldSort() {

    }

    private static void appendToFile() {

    }

    private static void countOccurrences() {

    }
}

