package org.misha.luxoft;

/*
 Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals, and return an
 array of the non-overlapping intervals that cover all the intervals in the input.
 Example 1:
 Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 Example 2:
 Input: intervals = [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

class Overlapping {
    private Interval[] intervals;

    Overlapping(Interval... intervals) {
        Arrays.sort(intervals);//O(intervals.length * log(intervals.length))
        this.intervals = intervals;
    }

    static class Interval implements Comparable<Interval> {
        int start;
        int end;
        int length;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
            length = end - start;
        }

        int[] interval() {
            return new int[]{start, end};
        }

        boolean contains(int i) {
            return start <= i && i <= end;
        }

        @Override
        public String toString() {
            return Arrays.toString(interval());
        }

        //equals is not needed
        @Override
        public int compareTo(final Interval o) {
            final int diff = start - o.start;
            return diff == 0 ? o.length - length : diff;
        }
    }

    private Iterator it() {
        return new Iterator(this);
    }

    static class Iterator {
        private Overlapping overlapping;
        private int length;
        private int i;

        Iterator(Overlapping overlapping) {
            this.overlapping = overlapping;
            length = overlapping.intervals.length;
            i = -1;
        }

        boolean hasNext() {
            return i < length - 1;
        }

        Interval next() {
            ++i;
            return overlapping.intervals[i];
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(intervals);
    }

    static Interval range(int a, int b) {
        return new Interval(a, b);
    }

    Collection merge() {// O(intervals.length)
        final Map<Integer, Interval> map = new LinkedHashMap<>();
        final Iterator it = it();
        Interval current;
        if (it.hasNext()) {
            final Interval next = it.next();
            map.put(next.start, next);
            current = next;
        } else {
            return map.values();
        }
        while (it.hasNext()) {
            final Interval interval = it.next();
            if (map.containsKey(current.start)) {
                if (current.contains(interval.start)) {//current interval overlaps the next one
                    if (interval.end >= current.end) {
                        current = range(current.start, interval.end);//merge
                        map.put(current.start, current);
                    }
                } else {
                    map.put(interval.start, interval);//the next interval
                    current = interval;//will be current from now
                }
            } else {//none among previous intervals has common start with the next interval
                map.put(interval.start, interval);
                current = interval;
            }
        }
        return map.values();
    }
}
