package Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;


public class EvenFieldQuickSort<T> implements SortStrategy<T> {
    private final Function<T, Integer> fieldExtractor;

    /**
     * @param fieldExtractor функция для извлечения числового поля из элементов
     * @throws IllegalArgumentException если fieldExtractor равен null
     */
    public EvenFieldQuickSort(Function<T, Integer> fieldExtractor) {
        if (fieldExtractor == null) {
            throw new IllegalArgumentException("fieldExtractor не может быть null");
        }
        this.fieldExtractor = fieldExtractor;
    }

    /**
     * Быстрая сортировка, упорядочивает только элементы с четными значениями поля.
     * @param list список для сортировки
     * @param comparator компаратор для сравнения элементов
     * @throws IllegalArgumentException если list или comparator равны null
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null) {
            throw new IllegalArgumentException("List не может быть null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator не может быть null");
        }

        if (list.size() <= 1) {
            return;
        }

        // Сбор четных элементов и их позиции
        List<T> evenElements = new ArrayList<>();
        List<Integer> evenPositions = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            int fieldValue = fieldExtractor.apply(element);

            if (fieldValue % 2 == 0) {
                evenElements.add(element);
                evenPositions.add(i);
            }
        }

        quickSortEvenElements(evenElements, comparator, 0, evenElements.size() - 1);

        // Возврат отсортированных четных элементов на их позиции
        for (int i = 0; i < evenPositions.size(); i++) {
            int originalPosition = evenPositions.get(i);
            list.set(originalPosition, evenElements.get(i));
        }
    }

    /**
     * Реализация алгоритма быстрой сортировки для списка четных элементов
     * @param evenElements список четных элементов для сортировки
     * @param comparator компаратор для сравнения элементов
     * @param low начальный индекс диапазона сортировки
     * @param high конечный индекс диапазона сортировки
     */
    private void quickSortEvenElements(List<T> evenElements, Comparator<T> comparator, int low, int high) {
        if (low < high) {
            // Разделяем список и получаем индекс опорного элемента
            int pivotIndex = partition(evenElements, comparator, low, high);

            // Тут происходит сортировка списков элементов выше и нижестоящие от опорного элемента
            quickSortEvenElements(evenElements, comparator, low, pivotIndex - 1);
            quickSortEvenElements(evenElements, comparator, pivotIndex + 1, high);
        }
    }

    /**
     * Метод разделения списка для быстрой сортировки
     * @param list список для разделения
     * @param comparator компаратор для сравнения элементов
     * @param low начальный индекс диапазона
     * @param high конечный индекс диапазона
     * @return индекс опорного элемента после разделения
     */
    private int partition(List<T> list, Comparator<T> comparator, int low, int high) {
        T pivot = list.get(high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<T> list, int i, int j) { //вспомогательный метод для изменения элементов списка местами
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}