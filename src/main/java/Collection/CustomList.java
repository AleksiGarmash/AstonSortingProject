package Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CustomList<T> implements Iterable<T> {

    /* TODO:
        ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ 3:
        Заполнение коллекций должно осуществляться посредством стримов.
        Коллекции для заполнения должны быть кастомными.
        */

    private final List<T> data = new ArrayList<>();

    public void add(T element) {
        data.add(element);
    }

    public T get(int index) {
        return data.get(index);
    }

    public void set(int index, T element) {
        data.set(index, element);
    }

    public int size() {
        return data.size();
    }

    public void clear() {
        data.clear();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public Stream<T> stream() {
        return data.stream();
    }

    public List<T> asList() {
        return data;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
