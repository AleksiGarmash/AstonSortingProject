package Tests;

import Collection.CustomList;
import Model.Person;
import Search.BinarySearch;

import java.util.Comparator;

public class BinarySearchTest {

    public static void main(String[] args) {
        System.out.println("=== Тест бинарного поиска ===");

        CustomList<Person> list = new CustomList<>();
        list.add(new Person.Builder().withName("Ivan").withAge(20).withEmail("a@a.com").build());
        list.add(new Person.Builder().withName("Petr").withAge(30).withEmail("b@b.com").build());
        list.add(new Person.Builder().withName("Anna").withAge(40).withEmail("c@c.com").build());

        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        int index = BinarySearch.binarySearch(list, new Person.Builder().withAge(30).build(), byAge);

        System.out.println("Ожидаем индекс 1, получили: " + index);
    }
}
