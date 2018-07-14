package org.misha.beanutils.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * author: misha
 * date: 4/18/16
 * time: 8:58 PM
 */
public class Node02 {
    private List<NodeEnum> list = new ArrayList<>();

    public void add(NodeEnum nodeEnum) {
        list.add(nodeEnum);
    }

    public NodeEnum get(int i) {
        return list.get(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node02 node02 = (Node02) o;
        if (list != null && node02.list != null) {
            if (list.size() != node02.list.size()) {
                return false;
            }
            int i = 0;
            for (NodeEnum nodeEnum : list) {
                if (!nodeEnum.equals(node02.list.get(i++))) {
                    return false;
                }
            }
        }
        return list != null || node02.list == null;
    }

    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }
}
