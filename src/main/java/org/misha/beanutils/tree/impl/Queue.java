package org.misha.beanutils.tree.impl;

import org.misha.beanutils.tree.Node;

import java.util.LinkedList;

/**
 * Created by misha. Date: 10/21/11 Time: 6:50 PM
 *
 * @param <T> a type of queue nodes content
 */
final class Queue<T> {
    private final LinkedList<Node<T>> list = new LinkedList<Node<T>>();

    Node<T> pop() {
        final Node<T> node = list.pop();
        if (!node.isLeaf()) {
            list.addAll(node.getChildren());
        }
        return node;
    }

    void push(final Node<T> n) {
        list.add(n);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
