package org.misha.beanutils.beans;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author: misha
 * date: 4/17/16
 * time: 10:02 AM
 */
public class Node10 implements Iterable<Node100> {
    private List<Node100> list = new ArrayList<>();

    @Override
    @Nonnull
    public Iterator<Node100> iterator() {
        return list.iterator();
    }

    public void add(Node100 node100) {
        list.add(node100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node10 node100s = (Node10) o;
        if (list != null && node100s.list != null) {
            if (list.size() != node100s.list.size()) {
                return false;
            }
            int i = 0;
            for (Node100 node100 : list) {
                if (!node100.equals(node100s.list.get(i++))) {
                    return false;
                }
            }
        }
        return list != null || node100s.list == null;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
