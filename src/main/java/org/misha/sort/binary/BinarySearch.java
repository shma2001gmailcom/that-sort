package org.misha.sort.binary;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

class BinarySearch<T extends Comparable<T>> {
    private static final Logger log = Logger.getLogger(BinarySearch.class);
    private final int high;
    private final int low;
    private final int middle;
    private final List<T> data;

    BinarySearch(final int low, final int high, @Nonnull final List<T> data) {
        this.high = high;
        this.low = low;
        this.data = data;
        middle = (low + high) / 2;
    }

    int search(@Nonnull T target) {
        for (BinarySearch<T> b = this; b.hasNext(); b = b.next(target)) {
            if (b.outOfRange(target, data)) {
                return -1;
            }
            int m = b.middle;
            if (data.get(m).equals(target)) {
                return m;
            }
        }
        return -1;
    }

    private BinarySearch<T> next(T target) {
        return data.get(middle).compareTo(target) < 0
               ? new BinarySearch<>(middle + 1, high, data)
               : new BinarySearch<>(low, middle - 1, data);
    }

    private boolean hasNext() {
        return low <= high;
    }

    private boolean outOfRange(T target, List<T> list) {
        return target.compareTo(list.get(low)) < 0 || target.compareTo(list.get(high)) > 0;
    }

    @Override
    public String toString() {
        return "BinarySearch{" +
                "high=" + high +
                ", low=" + low +
                '}';
    }

    public static void main(String... args) {
        List<Integer> data = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        log.debug(data.stream()
                      .map(i -> new BinarySearch<>(0, data.size() - 1, data).search(i) == data.indexOf(i))
                      .reduce((x, y) -> x && y)
                      .orElse(false));
    }
}
