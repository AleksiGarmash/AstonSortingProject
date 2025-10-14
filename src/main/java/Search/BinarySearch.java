package Search;

import Collection.CustomList;

import java.util.Comparator;
import java.util.List;

public class BinarySearch {

    public static <T> int binarySearch(CustomList<T> list, T key, Comparator<T> cmp) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int m = (l + r) >>> 1;
            int c = cmp.compare(list.get(m), key);

            if (c == 0) return m;
            if (c < 0) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }

}

