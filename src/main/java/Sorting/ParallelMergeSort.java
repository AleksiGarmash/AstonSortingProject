package Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


//Многопоточная реализация сортировки слиянием ThreadPool 2 потока
public class ParallelMergeSort<T> implements SortStrategy<T> {

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<T> temp = new ArrayList<>(list);

        try {
            int mid = list.size() / 2;
            // Два потока исполняют сортировку левой и правой части соответственно
            Future<?> leftFuture = executor.submit(() -> {
                sequentialMergeSort(list, temp, 0, mid, comparator);
            });

            Future<?> rightFuture = executor.submit(() -> {
                sequentialMergeSort(list, temp, mid + 1, list.size() - 1, comparator);
            });

            // Ждем завершения обеих половин
            leftFuture.get();
            rightFuture.get();

            // Сливаем результаты
            merge(list, temp, 0, mid, list.size() - 1, comparator);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при многопоточной сортировке", e);
        } finally {
            executor.shutdown();
        }
    }

    private void sequentialMergeSort(List<T> list, List<T> temp, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sequentialMergeSort(list, temp, left, mid, comparator);
            sequentialMergeSort(list, temp, mid + 1, right, comparator);
            // соединяем отсортированные половины
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