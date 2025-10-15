package Tests;

import Collection.CustomList;
import Model.Person;
import Sorting.ParallelMergeSort;

import java.util.Comparator;

public class ParallelSortTest {

    public static void main(String[] args) {
        System.out.println("=== Тест многопоточной сортировки ===");

        CustomList<Person> list = new CustomList<>();
        list.add(new Person.Builder().withName("Ivan").withAge(35).withEmail("a@a.com").build());
        list.add(new Person.Builder().withName("Petr").withAge(20).withEmail("b@b.com").build());
        list.add(new Person.Builder().withName("Anna").withAge(25).withEmail("c@c.com").build());

        ParallelMergeSort<Person> sorter = new ParallelMergeSort<>();
        sorter.sort(list.asList(), Comparator.comparing(Person::getAge));
        System.out.println("Отсортировано: " + list);
    }
}
