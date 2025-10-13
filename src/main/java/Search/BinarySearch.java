package Search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearch {

    /* TODO: Задание D
        Реализовать бинарный поиск с использованием универсальных дженериков под любые классы программы */
    public static <T> int binarySearch(List<T> list, T key, Comparator<T> cmp) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int m = (l + r) >>> 1;
            int c = cmp.compare(list.get(m), key);
            if (c == 0) return m;
            if (c < 0) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }
    /**
     * проверка: поиск по списку кастомных объектов с Comparator.
     */
    public static void main(String[] args) {
        record Person(String name, int age) {}

        List<Person> people = new ArrayList<>(List.of(
                new Person("Alice", 20),
                new Person("Dan", 25),
                new Person("Max", 30),
                new Person("Anna", 35)
        ));

        // Сортируем по возрасту, чтобы бинарный поиск работал корректно
        people.sort(Comparator.comparingInt(Person::age));

        // Ищем человека с возрастом 30
        Person target = new Person("?", 30);

        int index = binarySearch(people, target, Comparator.comparingInt(Person::age));

        System.out.println("Data: " + people);
        System.out.println("Searching for age 30...");
        System.out.println(index >= 0
                ? "Найден: " + people.get(index)
                : "Не найден");
    }

}

