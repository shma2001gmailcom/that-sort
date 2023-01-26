package org.misha.pyramid;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {
    private final Node<String> node0 = Node.makeNode("0");
    private final Node<String> node00 = Node.makeNode("00");
    private final Node<String> node01 = Node.makeNode("01");
    private final Node<String> node000 = Node.makeNode("000");
    private final Node<String> node001 = Node.makeNode("001");
    private final Node<String> node010 = Node.makeNode("010");
    private final Node<String> node011 = Node.makeNode("011");

    @Before
    @SuppressWarnings("unchecked warargs")
    public void before() {
        node0.addChild(node00, node01);
        node00.addChild(node000, node001);
        node01.addChild(node010, node011);
    }

    @Test
    public void getParent() {
        assertEquals(node00.parent(), node0);
        assertEquals(node01.parent(), node0);
    }

    @Test
    public void isLeaf() {
        assertTrue(node000.isLeaf());
    }

    @Test
    public void removeChild() {
        node00.removeChild(node000);
        assertNull(node000.parent());
        node00.addChild(node000);
        assertTrue(node00.hasSuchThat(n -> n == node000));
    }
}