package Sorting;

import java.util.Comparator;
import java.util.List;

public class BubbleSort<T> implements SortStrategy<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) return;

        boolean swapped;
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
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