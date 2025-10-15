package Tests;

import Collection.CustomList;
import Model.Person;
import Sorting.EvenFieldBubbleSort;
import Sorting.EvenFieldMergeSort;
import Sorting.EvenFieldQuickSort;
import Sorting.SortStrategy;

import java.util.Comparator;

public class EvenFieldSortTest {

    public static void main(String[] args) {
        System.out.println("=== Тест сортировок по четным полям ===");

        CustomList<Person> list = new CustomList<>();
        list.add(new Person.Builder().withName("Ivan").withAge(8).withEmail("a@a.com").build());
        list.add(new Person.Builder().withName("Petr").withAge(5).withEmail("b@b.com").build());
        list.add(new Person.Builder().withName("Anna").withAge(2).withEmail("c@c.com").build());
        list.add(new Person.Builder().withName("Olga").withAge(3).withEmail("d@d.com").build());

        Comparator<Person> byAge = Comparator.comparing(Person::getAge);

        runEvenTest(new EvenFieldQuickSort<>(Person::getAge), list, byAge, "EvenFieldQuickSort");
        runEvenTest(new EvenFieldBubbleSort<>(Person::getAge), list, byAge, "EvenFieldBubbleSort");
        runEvenTest(new EvenFieldMergeSort<>(Person::getAge), list, byAge, "EvenFieldMergeSort");
    }

    private static void runEvenTest(SortStrategy<Person> strategy, CustomList<Person> original, Comparator<Person> comparator, String name) {
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
