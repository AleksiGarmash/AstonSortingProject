package Tasks;

import Collection.CustomList;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
/**
 * Многопоточный подсчёт вхождений: разбиваем список на части, считаем параллельно и суммируем.
 */
public class CountOccurrencesTask {

    public static <T> int countOccurrences(CustomList<T> list, Predicate<T> predicate, ExecutorService pool, int parts) {
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
}
