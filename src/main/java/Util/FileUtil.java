package Util;

import Collection.CustomList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        List<String> list = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static <T> void appendToFile(String file, CustomList<T> items) {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (T t : items) {
                bw.write(t.toString());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
