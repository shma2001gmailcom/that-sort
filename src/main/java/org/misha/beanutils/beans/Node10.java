package org.misha.beanutils.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * author: misha
 * date: 4/17/16
 * time: 10:02 AM
 */
public class Node10 implements Iterable<Node100> {
    private List<Node100> list = new ArrayList<Node100>();

    @Override
    public Iterator<Node100> iterator() {
        return list.iterator();
    }

    public void add(Node100 node100) {
        list.add(node100);
    }
}
