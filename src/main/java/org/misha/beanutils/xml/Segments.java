package org.misha.beanutils.xml;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * author: misha
 * date: 5/1/16
 * time: 7:40 PM
 */
public class Segments {
    private final TreeMap<Integer, Segment> byLeftBound =
            new TreeMap<Integer, Segment>(new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o2, o1);
        }
    });
    private final TreeMap<Integer, Segment> byRightBound = new TreeMap<Integer, Segment>();

    public void add(Segment s) {
        byLeftBound.put(s.getStartIndex(), s);
        byRightBound.put(s.getEndIndex(), s);
    }

    public Segment nexLeft(Segment s) {
        return byLeftBound.lowerEntry(s.getStartIndex()).getValue();
    }

    public Segment nexRight(Segment s) {
        return byRightBound.higherEntry(s.getEndIndex()).getValue();
    }
}
