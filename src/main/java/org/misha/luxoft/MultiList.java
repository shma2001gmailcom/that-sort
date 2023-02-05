package org.misha.luxoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class MultiList<T> {
    private final List<MultiList<T>> children;
    private final T data;

    private MultiList(List<MultiList<T>> children) {
        assert children != null : "children shouldn't be null";
        this.data = null;
        this.children = children;
    }

    private MultiList(T data) {
        assert data != null : "data shouldn't be null";
        this.data = data;
        this.children = null;
    }

    @SafeVarargs
    static <S> MultiList<S> withChildren(MultiList<S>... children) {
        final List<MultiList<S>> list = new ArrayList<>(Arrays.asList(children));
        return new MultiList<>(list);
    }

    static <S> MultiList<S> withData(S data) {
        return new MultiList<>(data);
    }

    boolean isList() {
        return data == null && children != null;
    }

    List<T> asList() {
        final LinkedList<T> result = new LinkedList<>();
        class Stack {
            private final LinkedList<MultiList<T>> list = new LinkedList<>();

            private void push(MultiList<T> m) {
                list.push(m);
            }

            private MultiList<T> pop() {
                final MultiList<T> pop = list.pop();
                if (pop.isList()) {
                    final List<MultiList<T>> elements = pop.children;
                    assert elements != null;
                    for (MultiList<T> child : elements) {
                        list.push(child);
                    }
                }
                return pop;
            }

            boolean isEmpty() {
                return list.isEmpty();
            }
        }
        final Stack stack = new Stack();
        stack.push(this);
        while (!stack.isEmpty()) {
            final MultiList<T> pop = stack.pop();
            if (!pop.isList()) {
                result.addFirst(pop.data);
            }
        }
        return result;
    }
}
