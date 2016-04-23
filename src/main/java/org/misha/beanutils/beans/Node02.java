package org.misha.beanutils.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * author: misha
 * date: 4/18/16
 * time: 8:58 PM
 */
public class Node02 {
    private List<NodeEnum> list = new ArrayList<NodeEnum>();

    public void add(NodeEnum nodeEnum) {
        list.add(nodeEnum);
    }

    public NodeEnum get(int i) {
        return list.get(i);
    }
}
