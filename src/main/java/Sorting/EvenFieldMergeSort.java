package Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;

public class EvenFieldMergeSort<T> implements SortStrategy<T> {
    private final Function<T, Integer> fieldExtractor;

    /**
     * @param fieldExtractor функция для извлечения числового поля из элементов
     * @throws IllegalArgumentException если fieldExtractor равен null
     */
    public EvenFieldMergeSort(Function<T, Integer> fieldExtractor) {
        if (fieldExtractor == null) {
            throw new IllegalArgumentException("fieldExtractor не может быть null");
        }
        this.fieldExtractor = fieldExtractor;
    }

    /**
     * Сортирует список, упорядочивая только элементы с четными значениями поля.
     * @param list список для сортировки
     * @param comparator компаратор для сравнения элементов
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
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

        mergeSortEvenElements(evenElements, comparator);

        // Возвращаем отсортированные четные элементы на их позиции
        for (int i = 0; i < evenPositions.size(); i++) {
            int originalPosition = evenPositions.get(i);
            list.set(originalPosition, evenElements.get(i));
        }
    }

    /**
     * Сортировка слиянием для списка четных элементов
     * @param evenElements список четных элементов для сортировки
     * @param comparator компаратор для сравнения элементов
     */
    private void mergeSortEvenElements(List<T> evenElements, Comparator<T> comparator) {
        if (evenElements.size() <= 1) {
            return;
        }

        // Создаем временный список для слияния
        List<T> temp = new ArrayList<>(evenElements);
        mergeSort(evenElements, temp, 0, evenElements.size() - 1, comparator);
    }

    /**
     * метод сортировки слиянием
     * @param list исходный список
     * @param temp временный список для слияния
     * @param left начальный индекс текущего сегмента
     * @param right конечный индекс текущего сегмента
     * @param comparator компаратор для сравнения элементов
     */
    private void mergeSort(List<T> list, List<T> temp, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Рекурсивно сортируем левую и правую половины
            mergeSort(list, temp, left, mid, comparator);
            mergeSort(list, temp, mid + 1, right, comparator);

            // Объединяем отсортированные половины
            merge(list, temp, left, mid, right, comparator);
        }
    }

    /**
     * Объединение двух отсортированных сегментов в один
     * @param list исходный список
     * @param temp временный список для слияния
     * @param left начальный индекс левого сегмента
     * @param mid конечный индекс левого сегмента
     * @param right конечный индекс правого сегмента
     * @param comparator компаратор для сравнения элементов
     */
    private void merge(List<T> list, List<T> temp, int left, int mid, int right, Comparator<T> comparator) {
        // Копируем текущий сегмент во временный список
        for (int i = left; i <= right; i++) {
            temp.set(i, list.get(i));
        }

        int i = left;      // Указатель на начало левой половины
        int j = mid + 1;   // Указатель на начало правой половины
        int k = left;      // Указатель на текущую позицию в исходном списке

        // Сливаем две половины, выбирая меньший элемент на каждом шаге
        while (i <= mid && j <= right) {
            if (comparator.compare(temp.get(i), temp.get(j)) <= 0) {
                list.set(k, temp.get(i));
                i++;
            } else {
                list.set(k, temp.get(j));
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы из левой и правой половины (если есть)
        while (i <= mid) {
            list.set(k, temp.get(i));
            i++;
            k++;
        }
        while (j <= right) {
            list.set(k, temp.get(j));
            j++;
            k++;
        }
    }
}