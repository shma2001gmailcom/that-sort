package org.misha.interview;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * author: misha
 * date: 6/24/15 8:53 PM.
 */
public class Interview {
    /**
     * Given infinite stream of integers. Using only JDK library implement class which would process those
     * integers and at any moment will be able to provide at most N unique biggest values among all processed.
     * Class interface:
     * 1. N is class constructor argument, immutable.
     * 2. void push(int val) - process integers one-by-one.
     * 3. Collection<Integer> top().
     */
    private final int N;

    public Interview(final int N) {
        this.N = N;
        topN = new TreeSet<>(Comparator.reverseOrder());
    }

    private final TreeSet<Integer> topN;

    public void push(int val) {
        if (topN.size() < N) {
            topN.add(val);
        } else {
            if (val > topN.last()) topN.add(val);
            if (topN.size() > N) topN.remove(topN.last());
        }
    }

    public Collection<Integer> top() {
        return topN;
    }
}
