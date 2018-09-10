package org.misha.beanutils.beans;

import java.util.Date;

/**
 * author: misha
 * date: 5/4/16
 * time: 9:35 PM
 */
public class RootMaker {

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
        Node02 node02 = new Node02();
        node02.add(NodeEnum.ONE);
        node02.add(NodeEnum.TWO);
        node02.add(NodeEnum.ONE);
        node0.setNode02(node02);
        return root;
    }
}
