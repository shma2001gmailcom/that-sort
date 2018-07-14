package org.misha.beanutils.beans;

/**
 * author: misha
 * date: 4/17/16
 * time: 6:19 PM
 */
public class Node11 {
    private String n11;

    public String getN11() {
        return n11;
    }

    public void setN11(String n11) {
        this.n11 = n11;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node11 node11 = (Node11) o;
        return n11 != null ? n11.equals(node11.n11) : node11.n11 == null;
    }

    @Override
    public int hashCode() {
        return n11 != null ? n11.hashCode() : 0;
    }
}
