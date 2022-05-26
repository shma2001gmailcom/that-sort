package org.misha.luxoft;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class Tin {
    //is it known how much time is needed for a person, standing in a line to the washing room, for washing
    //there is washingMachines washing machines in the washing room
    //calculate a time amount needed in order to all people done work.
    private static int wash(int washingMachines, int... washingTimes) {//O(n*n)
        List<Integer> people = new LinkedList<>();
        IntStream.of(washingTimes).forEach(people::add);//O(n)
        int result = 0;
        while (!people.isEmpty()) {
            Integer time = people.get(minIndex(people, washingMachines));
            result += time;
            for (int i = 0; i < people.size() && i < washingMachines; people.set(i, people.get(i) - time), ++i) ;
            people.removeIf(i -> i == 0);
        }
        return result;
    }

    private static int wash0(int washingMachines, LinkedList<Integer> people, int result) {//O(n*n)
        if (people.isEmpty()) return result;
        Integer time = people.get(minIndex(people, washingMachines));
        result += time;
        for (int i = 0; i < people.size() && i < washingMachines; people.set(i, people.get(i) - time), ++i) ;
        people.removeIf(i -> i == 0);
        return wash0(washingMachines, people, result);
    }

    private static int minIndex(List<Integer> list, int before) {
        int index = 0;
        int min = list.get(index);
        for (int i = 0; i < list.size() && i < before; i++) {
            Integer k = list.get(i);
            if (min > k) {
                min = k;
                index = i;
            }
        }
        return index;
    }

    //in an array find two integers which sum is k
    private static String search(int[] a, int k) {//O(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : a) {//O(n)
            map.put(key, k - key);
        }
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {//O(n)
            if (map.get(e.getValue()) != null) {//O(1)
                return e.getKey() + " " + e.getValue();
            }
        }
        return "no";
    }

    @Test
    public void testSearch() {
        int[] a = {4, 5, 3};
        assertEquals("3 5", search(a, 8));
        a = new int[]{1, 1, 1};
        assertEquals("no", search(a, 1));
    }

    @Test
    public void testWash() {
        assertEquals(9, wash(3, 1, 2, 3, 4, 5, 6));
        assertEquals(9, wash0(3, new LinkedList<>(newArrayList(1, 2, 3, 4, 5, 6)), 0));
        assertEquals(11, wash(6, 9, 1, 11, 3, 8, 8, 8, 1, 2));
        assertEquals(11, wash0(6, new LinkedList<>(newArrayList(9, 1, 11, 3, 8, 8, 8, 1, 2)), 0));
    }
}
