package App;

import Model.DataInputFromFile;
import Model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {

    /* TODO: Задание Е (меню, логика работы, интеграция всех частей)
      Реализовать:
      1. Возможность выбирать варианты заполнения исходного массива данных (из файла, рандом, вручную) и его длину
      2. Возможность найти какой-либо элемент отсортированной коллекции при помощи алгоритма бинарного поиска
   */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите режим загрузки данных:");
            System.out.println("1. Загрузить из файла");
            System.out.println("2. Генерировать случайно");
            System.out.println("3. Ввести вручную");
            System.out.println("4. Завершить работу");

            int modeChoice = scanner.nextInt();
            scanner.nextLine();

            List<Person> data = new ArrayList<>();

            switch (modeChoice) {
                case 1:
                    System.out.print("Введите путь к файлу: ");
                    String filename = scanner.nextLine();
                    try {
                        data = DataInputFromFile.loadFromFile(filename);
                    } catch (IOException ioex) {
                        System.err.println(ioex.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Количество генерируемых записей: ");
                    int recordsCount = scanner.nextInt();
                    data = DataInputFromFile.generateRandomly(recordsCount);
                    break;
                case 3:
                    data = DataInputFromFile.enterManually(scanner);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор.");
                    continue;
            }
            System.out.println("Исходные данные:");
            printCollection(data);
        }
    }

    private static void printCollection(Collection<?> collection) {
        for (Object obj : collection) {
            System.out.println(obj);
        }
    }
}
