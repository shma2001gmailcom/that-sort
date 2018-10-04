package org.misha.beanutils.beans;

/**
 * author: misha
 * date: 4/17/16
 * time: 9:59 AM
 */
public class Node01 {
    private String string01;

    public String getString01() {
        return string01;
    }

    void setString01(String string01) {
        this.string01 = string01;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node01 node01 = (Node01) o;
        return string01 != null ? string01.equals(node01.string01) : node01.string01 == null;
    }

    @Override
    public int hashCode() {
        return string01 != null ? string01.hashCode() : 0;
    }
}
