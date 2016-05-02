package org.misha.beanutils.xml;

import java.util.Comparator;
import java.util.Map;
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
            return o2.compareTo(o1);
        }
    });

    public void add(Segment s) {
        byLeftBound.put(s.getStartIndex(), s);
    }

    public Segment nexLeft(Segment s) {
        if(s == null) {
            return null;
        }
        final Map.Entry<Integer, Segment> higherEntry = byLeftBound.higherEntry(s.getStartIndex());
        return higherEntry == null ? null : higherEntry.getValue();
    }
}
