package Sorting;

import java.util.Comparator;
import java.util.List;
public class Sorter<T> {
    private SortStrategy<T> sortingStrategy;

    /**
     * @param sortingStrategy стратегия сортировки, которая будет использоваться по умолчанию
     * @throws IllegalArgumentException если sortingStrategy равен null
     */
    public Sorter(SortStrategy<T> sortingStrategy) {
        if (sortingStrategy == null) {
            throw new IllegalArgumentException("Стратегия не может быть null");
        }
        this.sortingStrategy = sortingStrategy;
    }

    /**
     * @param sortingStrategy новая стратегия сортировки
     * @throws IllegalArgumentException если sortingStrategy равен null
     */
    public void setSortingStrategy(SortStrategy<T> sortingStrategy) {
        if (sortingStrategy == null) {
            throw new IllegalArgumentException("Стратегия не может быть null");
        }
        this.sortingStrategy = sortingStrategy;
    }

    /** Сортировка
     * @param list       список для сортировки
     * @param comparator компаратор, определяющий порядок сортировки
     * @throws IllegalArgumentException если list или comparator равны null
     */
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || comparator == null) {
            throw new IllegalArgumentException("List и Comparator не могут быть null");
        }
        sortingStrategy.sort(list, comparator);
    }

    /**
     * @return текущая стратегия сортировки
     */
    public SortStrategy<T> getSortingStrategy() {
        return sortingStrategy;
    }
}
