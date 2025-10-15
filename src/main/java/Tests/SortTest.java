package Tests;

import Collection.CustomList;
import Model.Person;
import Sorting.BubbleSort;
import Sorting.MergeSort;
import Sorting.QuickSort;
import Sorting.SortStrategy;

import java.util.Comparator;

public class SortTest {

    public static void main(String[] args) {
        System.out.println("=== Тест сортировок ===");

        CustomList<Person> list = new CustomList<>();
        list.add(new Person.Builder().withName("Ivan").withAge(30).withEmail("a@a.com").build());
        list.add(new Person.Builder().withName("Petr").withAge(25).withEmail("b@b.com").build());
        list.add(new Person.Builder().withName("Anna").withAge(40).withEmail("c@c.com").build());

        Comparator<Person> byAge = Comparator.comparing(Person::getAge);

        runTest(new QuickSort<>(), list, byAge, "QuickSort");
        runTest(new BubbleSort<>(), list, byAge, "BubbleSort");
        runTest(new MergeSort<>(), list, byAge, "MergeSort");
    }

    private static void runTest(SortStrategy<Person> strategy, CustomList<Person> original, Comparator<Person> comparator, String name) {
        CustomList<Person> listCopy = copyList(original);
        strategy.sort(listCopy.asList(), comparator);
        System.out.println(name + " => " + listCopy);
    }

    private static CustomList<Person> copyList(CustomList<Person> list) {
        CustomList<Person> copy = new CustomList<>();
        list.forEach(copy::add);
        return copy;
    }
}
