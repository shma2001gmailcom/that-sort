package org.misha.beanutils.beans;

/**
 * author: misha
 * date: 4/17/16
 * time: 9:55 AM
 */
public class Node0 {
    private Node00 node00;
    private Node01 node01;
    private Node02 node02;

    public Node00 getNode00() {
        return node00;
    }

    void setNode00(Node00 node00) {
        this.node00 = node00;
    }

    public Node01 getNode01() {
        return node01;
    }

    void setNode01(Node01 node01) {
        this.node01 = node01;
    }

    public Node02 getNode02() {
        return node02;
    }

    void setNode02(Node02 node02) {
        this.node02 = node02;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node0 node0 = (Node0) o;
        return (node00 != null ? node00.equals(node0.node00) : node0.node00 == null) && (node01 != null ? node01
                .equals(node0.node01) : node0.node01 == null) && (node02 != null ? node02.equals(node0.node02)
                                                                                 : node0.node02 == null);
    }

    @Override
    public int hashCode() {
        int result = node00 != null ? node00.hashCode() : 0;
        result = 31 * result + (node01 != null ? node01.hashCode() : 0);
        result = 31 * result + (node02 != null ? node02.hashCode() : 0);
        return result;
    }
}
