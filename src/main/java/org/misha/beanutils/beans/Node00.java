package org.misha.beanutils.beans;

/**
 * author: misha
 * date: 4/17/16
 * time: 9:59 AM
 */
public class Node00 {
    private Integer integer00;

    public Integer getInteger00() {
        return integer00;
    }

    void setInteger00(Integer integer00) {
        this.integer00 = integer00;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node00 node00 = (Node00) o;
        return integer00 != null ? integer00.equals(node00.integer00) : node00.integer00 == null;
    }

    @Override
    public int hashCode() {
        return integer00 != null ? integer00.hashCode() : 0;
    }
}
