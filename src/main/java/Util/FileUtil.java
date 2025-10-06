package Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public class FileUtil {

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
}
