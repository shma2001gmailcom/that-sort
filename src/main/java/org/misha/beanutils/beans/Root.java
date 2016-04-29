package org.misha.beanutils.beans;

import java.util.Date;

/**
 * author: misha
 * date: 4/17/16
 * time: 9:54 AM
 */
public class Root {
    private Node0 node0;
    private Node1 node1;
    private Date date;

    public Node0 getNode0() {
        return node0;
    }

    public void setNode0(Node0 node0) {
        this.node0 = node0;
    }

    public Node1 getNode1() {
        return node1;
    }

    public void setNode1(Node1 node1) {
        this.node1 = node1;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class RootMaker {

        public static Root makeRoot() {
            Root root = new Root();
            Node0 node0 = new Node0();
            Node00 node00 = new Node00();
            node00.setInteger00(0);
            node0.setNode00(node00);
            Node01 node01 = new Node01();
            node01.setString01("string01");
            node0.setNode01(node01);
            root.setNode0(node0);
            Node1 node1 = new Node1();
            Node10 node10 = new Node10();
            Node100 node100 = new Node100();
            Node100 anotherNode100 = new Node100();
            node100.setS("s0");
            anotherNode100.setS("s1");
            node10.add(node100);
            node10.add(anotherNode100);
            Node11 node11 = new Node11();
            node1.add(node11);
            node1.add(node11);
            node1.setNode10(node10);
            root.setNode1(node1);
            root.setDate(new Date());
            return root;
        }
    }
}
