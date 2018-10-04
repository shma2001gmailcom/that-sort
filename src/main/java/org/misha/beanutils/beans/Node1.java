package org.misha.beanutils.beans;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author: misha
 * date: 4/17/16
 * time: 9:56 AM
 */
public class Node1 implements Iterable<Node11> {
    private Node10 node10;
    private List<Node11> list = new ArrayList<>();

    public Node10 getNode10() {
        return node10;
    }

    void setNode10(Node10 node10) {
        this.node10 = node10;
    }

    @Override
    @Nonnull
    public Iterator<Node11> iterator() {
        return list.iterator();
    }

    public void add(Node11 node11) {
        list.add(node11);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node1 node11s = (Node1) o;
        if (list != null && node11s.list != null) {
            if (list.size() != node11s.list.size()) {
                return false;
            }
            int i = 0;
            for (Node11 node11 : list) {
                if (!node11.equals(node11s.list.get(i++))) {
                    return false;
                }
            }
        }
        if (list == null) {
            return node11s.list == null;
        }
        return !(node10 != null ? !node10.equals(node11s.node10) : node11s.node10 != null);
    }

    @Override
    public int hashCode() {
        int result = node10 != null ? node10.hashCode() : 0;
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }
}
