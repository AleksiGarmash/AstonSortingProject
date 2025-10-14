package Sorting;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Многопоточная реализация сортировки слиянием ThreadPool 2 потока
public class ParallelMergeSort<T> implements SortStrategy<T> {
    private final ExecutorService executor;

    public ParallelMergeSort() {
        this.executor = Executors.newFixedThreadPool(2);
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return;
        }

        List<T> temp = new ArrayList<>(list);
        try {
            // Запуск многопоточной сортировки
            parallelMergeSort(list, temp, 0, list.size() - 1, comparator).get();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при многопоточной сортировке", e);
        }
        executor.shutdown();
    }

    /**
     * Метод многопоточной сортировки слиянием
     * @return Future для отслеживания завершения задачи
     */
    private Future<?> parallelMergeSort(List<T> list, List<T> temp, int left, int right, Comparator<T> comparator) {
        if (left >= right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        // Два потока исполняют сортировку левой и правой части соответственно
        Future<?> leftFuture = executor.submit(() ->
                parallelMergeSort(list, temp, left, mid, comparator));
        Future<?> rightFuture = executor.submit(() ->
                parallelMergeSort(list, temp, mid + 1, right, comparator));

        // Ждем завершения обеих задач

        return executor.submit(() -> {
            try {
                if (leftFuture != null) leftFuture.get();
                if (rightFuture != null) rightFuture.get();

                // соединяем отсортированные половины
                merge(list, temp, left, mid, right, comparator);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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