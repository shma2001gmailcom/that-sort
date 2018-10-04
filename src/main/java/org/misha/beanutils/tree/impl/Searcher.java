package org.misha.beanutils.tree.impl;

import org.misha.beanutils.tree.Node;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: mshevelin Date: 10/24/11 Time: 10:03 AM
 */
public abstract class Searcher<T> implements Iterable<Node<T>> {
    private final Node<T> root;
    private final Set<Node<T>> results = new HashSet<>();

    protected Searcher(final Node<T> tree) {
        root = tree;
    }

    public void search() {
        Node<T> node;
        Queue<T> queue = new Queue<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            node = queue.pop();
            if (isSuitable(node)) {
                results.add(node);
            }
        }
    }

    @Nonnull
    @Override
    public Iterator<Node<T>> iterator() {
        return results.iterator();
    }

    public int size() {
        return results.size();
    }

    public abstract boolean isSuitable(Node<T> node);
}
