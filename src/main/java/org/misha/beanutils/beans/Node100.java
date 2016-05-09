package org.misha.beanutils.beans;

/**
 * author: misha
 * date: 4/17/16
 * time: 10:03 AM
 */
public class Node100 {
    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node100 node100 = (Node100) o;
        if (s != null ? !s.equals(node100.s) : node100.s != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return s != null ? s.hashCode() : 0;
    }
}
