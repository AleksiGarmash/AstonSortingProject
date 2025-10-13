package Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

public class EvenFieldBubbleSort<T> implements SortStrategy<T> {
    private final Function<T, Integer> fieldExtractor;

    /**
     * @param fieldExtractor функция для извлечения числового поля из элементов
     * @throws IllegalArgumentException если fieldExtractor равен null
     */
    public EvenFieldBubbleSort(Function<T, Integer> fieldExtractor) {
        if (fieldExtractor == null) {
            throw new IllegalArgumentException("fieldExtractor Не может быть null");
        }
        this.fieldExtractor = fieldExtractor;
    }

    /**
     * Сортирует список, упорядочивая только элементы с четными значениями поля. Элементы с нечетными значениями сохраняют свои исходные позиции.
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

        //Сбор четных элементов и их позиции
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

        bubbleSortEvenElements(evenElements, comparator);

        //Возвращаем отсортированные четные элементы на их позиции
        for (int i = 0; i < evenPositions.size(); i++) {
            int originalPosition = evenPositions.get(i);
            list.set(originalPosition, evenElements.get(i));
        }
    }

    /**
     * @param evenElements список четных элементов для сортировки
     * @param comparator компаратор для сравнения элементов
     */
    private void bubbleSortEvenElements(List<T> evenElements, Comparator<T> comparator) {
        if (evenElements.size() <= 1) {
            return;
        }

        boolean swapped;
        int n = evenElements.size();

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(evenElements.get(j), evenElements.get(j + 1)) > 0) {
                    swap(evenElements, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}