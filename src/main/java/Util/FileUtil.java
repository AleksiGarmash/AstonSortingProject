package Util;

import java.io.*;
import java.util.List;

public final class FileUtil {
    private FileUtil() {}

    /* TODO: Задание D
        Реализовать работу с файлом для дальнейшей валидации данных:
        1. Чтение данных из файла
        2. Запись данных в файл
        ...
        ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ 2:
        Реализовать функционал для записи отсортированных коллекций/найденных значений в файл в режиме добавления данных.
     */
    public static <T> void appendToFile(String file, List<T> items) {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (T t : items) {
                bw.write(t.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    /**
     * проверка: записывает строки в файл и выводит содержимое обратно.
     */
    public static void main(String[] args) {
        String path = "test_data.txt";
        List<String> data = List.of("one", "two", "three");

        appendToFile(path, data);
        System.out.println("Данные успешно записаны в файл: " + path);

        // Проверим, что записалось
        System.out.println("Содержимое файла:");
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
