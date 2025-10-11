package Sorting;

import java.util.Comparator;
import java.util.List;

public class QuickSort<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }
    /**
     * Метод быстрой сортировки
     * @param list список для сортировки
     * @param low начальный индекс
     * @param high конечный индекс
     * @param comparator компаратор для сравнения
     */
    private void quickSort(List<T> list, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, comparator);

            // Тут происходит сортирова списков элементов выше и нижестоящие от опорного элемента
            quickSort(list, low, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex + 1, high, comparator);
        }
    }
    /**
     * Метод разделения списка относительно опорного элемента
     * @param list список для разделения
     * @param low начальный индекс
     * @param high конечный индекс
     * @param comparator компаратор для сравнения
     * @return индекс опорного элемента после разделения
     */
    private int partition(List<T> list, int low, int high, Comparator<T> comparator) {
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
