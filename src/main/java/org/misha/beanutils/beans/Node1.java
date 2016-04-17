package org.misha.beanutils.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author: misha
 * date: 4/17/16
 * time: 9:56 AM
 */
public class Node1 implements Iterable<Node11>{
    private Node10 node10;
    private List<Node11> list = new ArrayList<Node11>();

    public Node10 getNode10() {
        return node10;
    }

    public void setNode10(Node10 node10) {
        this.node10 = node10;
    }

    @Override
    public Iterator<Node11> iterator() {
        return list.iterator();
    }

    public void add(Node11 node11) {
        list.add(node11);
    }
}
