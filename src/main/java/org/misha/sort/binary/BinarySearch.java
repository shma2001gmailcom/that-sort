package org.misha.sort.binary;

import org.apache.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

public final class BinarySearch<T extends Comparable<T>> {
    private static final Logger log = Logger.getLogger(BinarySearch.class);
    private final int high;
    private final int low;
    private final int middle;
    private final List<T> data;

    private BinarySearch(final int low, final int high, @Nonnull final List<T> data) {
        this.high = high;
        this.low = low;
        this.data = data;
        middle = (low + high) / 2;
    }

    public BinarySearch(@Nonnull final List<T> data) {
        this(0, data.size() - 1, data);
    }

    public int search(@Nonnull T target) {
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
}
