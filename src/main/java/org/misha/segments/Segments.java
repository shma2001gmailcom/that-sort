package org.misha.segments;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * author: misha
 * date: 5/1/16
 * time: 7:40 PM
 * <p>
 * there is one-to-one correspondence
 * between set of segments and set of
 * their left bounds by condition at {@link org.misha.segments.Segment}
 */
public final class Segments implements Iterable<Map.Entry<Integer, Segment>> {
    private final TreeMap<Integer, Segment> lefts;

    public Segments() {
        lefts = new TreeMap<>(reverser());
    }

    private static Reverser reverser() {
        return new Reverser();
    }

    public void add(final Segment s) {
        lefts.put(s.left(), s);
    }

    Segment nexLeft(final Segment s) {
        if (s == null) {
            return null;
        }
        final Map.Entry<Integer, Segment> higherEntry = lefts.higherEntry(s.left());
        return higherEntry == null ? null : higherEntry.getValue();
    }

    @Override
    public Iterator<Map.Entry<Integer, Segment>> iterator() {
        return lefts.entrySet().iterator();
    }

    private static class Reverser implements Comparator<Integer>, Serializable {
        private static final long serialVersionUID = -8890598769647883458L;

        @Override
        public int compare(final Integer i, final Integer j) {
            return j.compareTo(i);
        }
    }
}
