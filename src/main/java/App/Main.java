package App;

import Collection.CustomList;
import Model.DataGenerator;
import Model.Person;
import Search.BinarySearch;
import Sorting.*;
import Util.FileUtil;
import Util.Validation;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
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
                            "1. Заполнение данных вручную \n" +
                            "2. Заполнение данных рандомно \n" +
                            "3. Заполнение данных из файла \n" +
                            "4. Быстрая сортировка \n" +
                            "5. Сортировка пузырьком \n" +
                            "6. Сортировка слиянием \n" +
                            "7. Многопоточная сортировка слиянием (в 2 потока) \n" +
                            "8. Бинарный поиск \n" +
                            "== Дополнительные функции == \n" +
                            "9. Быстрая сортировка четных полей \n" +
                            "10. Сортировка пузырьком четных полей \n" +
                            "11. Сортировка слиянием четных полей \n" +
                            "12. Запись данных в файл\n" +
                            "13. Подсчет вхождений (многопоточно)\n" +
                            "14. Выход\n" +
                            "\nВыбор: "

            );

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> fillManual(list);
                case "2" -> fillRandom(list);
                case "3" -> fillFromFile(list);
                case "4" -> runSort(list, new QuickSort<>());
                case "5" -> runSort(list, new BubbleSort<>());
                case "6" -> runSort(list, new MergeSort<>());
                case "7" -> runSort(list, new ParallelMergeSort<>());
                case "8" -> runBinarySearch(list);
                case "9" -> runSort(list, new EvenFieldQuickSort<>(Person::getAge));
                case "10" -> runSort(list, new EvenFieldBubbleSort<>(Person::getAge));
                case "11" -> runSort(list, new EvenFieldMergeSort<>(Person::getAge));
                case "12" -> appendToFile(list);
                case "13" -> countOccurrences();
                case "14" -> {
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
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Запись №" + (i + 1) + ":");

            System.out.print("Имя: ");
            String name = scanner.nextLine();

            System.out.print("Возраст: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Электронная почта: ");
            String email = scanner.nextLine();

            try {
                list.add(new Person.Builder().withName(name).withAge(age).withEmail(email).build());
            } catch (Exception e) {
                System.out.println("Ошибка ввода! Повторите.");
                i--;
            }
        }

        System.out.println(list.toString());
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
                            .withAge(random.nextInt(80) + 5)
                            .withEmail(generator.generateRandomEmail())
                            .build()
            );
        }
        System.out.println(list.toString());
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
        System.out.println(list.toString());

    }

    private static <T> void runSort(CustomList<T> list, Sorting.SortStrategy<T> strategy) {

        if (list.isEmpty()) {
            System.out.println("Коллекция пуста!");
            return;
        }

        Sorter<T> sorter = new Sorter<>(strategy);
        sorter.sort( list.asList(), Comparator.comparing(Object::toString));
        System.out.println("Отсортировано\n" + list.toString());
    }

    private static void runBinarySearch(CustomList<Person> list) {

        if (list.isEmpty()) {
            System.out.println("Коллекция пуста!");
            return;
        }

        System.out.print("Имя для поиска: ");
        String key = scanner.nextLine();

        int index = BinarySearch.binarySearch(
                list,
                new Person.Builder().withName(key).build(),
                Comparator.comparing(Person::getName));

        System.out.println(index >= 0 ? "Найдено: " + list.get(index) : "Не найдено");
    }

    private static void appendToFile(CustomList<Person> list) {
        list.clear();

        System.out.print("Введите имя файла для записи: ");
        String file = scanner.nextLine();

        FileUtil.appendToFile(file, list);
        System.out.println("Данные записаны в файл!");
    }

    private static void countOccurrences() {

    }
}

