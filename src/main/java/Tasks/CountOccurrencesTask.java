package Tasks;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
/**
 * Многопоточный подсчёт вхождений: разбиваем список на части, считаем параллельно и суммируем.
 */
public final class CountOccurrencesTask {

    /* TODO:
        ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ 4:
        Реализовать многопоточный метод, подсчитывающий количество вхождений элемента N в коллекцию и выводящий результат в консоль.
     */
    private CountOccurrencesTask() {
    }

    public static <T> int countOccurences(List<T> list, Predicate<T> predicate, ExecutorService pool, int parts) {
        int n = list.size();
        if (n == 0) return 0;
        int chunk = Math.max(1, n / parts);
        AtomicInteger total = new AtomicInteger(0);
        List<Future<?>> futures = new CopyOnWriteArrayList<>();
        for (int i = 0; i < n; i += chunk) {
            int start = i;
            int end = Math.min(n, i + chunk);
            futures.add(pool.submit(() -> {
                int local = 0;
                for (int j = start; j < end; j++) {
                    if (predicate.test(list.get(j))) local++;
                }
                total.addAndGet(local);
            }));
        }
        // Ожидаем завершения всех задач
        for (Future<?> f : futures) {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return total.get();
    }

    /**
     * Простейшая проверка: создаёт список чисел и считает количество вхождений числа 5.
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        try {
            List<Integer> data = List.of(1, 5, 2, 5, 5, 7, 9, 5, 3, 5, 10);
            int result = countOccurences(data, x -> x == 5, pool, 3);
            System.out.println("Data: " + data);
            System.out.println("Count of 5 = " + result);
        } finally {
            pool.shutdown();
        }

    }
}
