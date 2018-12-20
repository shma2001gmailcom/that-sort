package org.misha.pyramid;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Node<T> implements Iterable<Node<T>> {
    private final Set<Node<T>> children = new HashSet<>();
    private T data;
    private Node<T> parent;

    static <S> Node<S> makeNode(S data) {
        Node<S> result = new Node<>();
        result.setData(data);
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> parent() {
        return parent;
    }

    @Override
    @Nonnull
    public Iterator<Node<T>> iterator() {
        return children.iterator();
    }

    @Override
    public void forEach(Consumer<? super Node<T>> action) {
        children.forEach(action);
    }

    @SafeVarargs
    public final void addChild(Node<T>... nodes) {
        Arrays.stream(nodes).forEach(n -> {
            children.add(n);
            n.parent = this;
        });
    }

    boolean isLeaf() {
        return children.isEmpty();
    }

    public void removeChild(Node<T> child) {
        if (child.parent == this) {
            children.remove(child);
            child.parent = null;
        }
    }

    void leaveParent() {
        if (parent != null) {
            parent.removeChild(this);
            this.parent = null;
        }
    }

    boolean hasSuchThat(Predicate<Node<T>> condition) {
        return children.stream().anyMatch(condition);
    }

    Set<Node> getSuchThat(Predicate<Node<T>> condition) {
        return children.stream().filter(condition).collect(Collectors.toSet());
    }
}
