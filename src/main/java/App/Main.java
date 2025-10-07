package App;

import Collection.CustomList;
import Model.CustomClass;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    /* TODO: Задание Е (меню, логика работы, интеграция всех частей)
        Реализовать:
        1. Возможность выбирать варианты заполнения исходного массива данных (из файла, рандом, вручную) и его длину
        2. Возможность найти какой-либо элемент отсортированной коллекции при помощи алгоритма бинарного поиска
     */


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CustomList<CustomClass> list = new CustomList<>();
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
                case "6" -> runBinarySearch();
                case "7" -> runEvenOddSort();
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

    private static void fillManual(CustomList<CustomClass> list) {
        list.clear();

    }

    private static void fillRandom(CustomList<CustomClass> list) {
        list.clear();
        Random random = new Random();

    }

    private static void fillFromFile(CustomList<CustomClass> list) {
        list.clear();
        System.out.println("Файл: ");
        String path = scanner.nextLine();

    }

    private static <T> void runSort() {

    }

    private static void runBinarySearch() {

    }

    private static void runEvenOddSort() {

    }

    private static void appendToFile() {

    }

    private static void countOccurrences() {

    }
}

