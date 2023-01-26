package org.misha.luxoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class MultiList {
    private final List<MultiList> children;
    private final Integer data;

    private MultiList(List<MultiList> children) {
        assert children != null : "children shouldn't be null";
        this.data = null;
        this.children = children;
    }

    private MultiList(Integer data) {
        assert data != null : "data shouldn't be null";
        this.data = data;
        this.children = null;
    }

    boolean isList() {
        return data == null && children != null;
    }

    static MultiList withChildren(MultiList... children) {
        final List<MultiList> list = new ArrayList<>();
        list.addAll(Arrays.asList(children));
        return new MultiList(list);
    }

    static MultiList withData(Integer data) {
        return new MultiList(data);
    }

    List<Integer> asList() {
        final LinkedList<Integer> result = new LinkedList<>();
        class Stack {
            private LinkedList<MultiList> list = new LinkedList<>();

            private void push(MultiList m) {
                list.push(m);
            }

            private MultiList pop() {
                final MultiList pop = list.pop();
                if (pop.isList()) {
                    final List<MultiList> elements = pop.children;
                    assert elements != null;
                    for (MultiList child : elements) {
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
            final MultiList pop = stack.pop();
            if (!pop.isList()) {
                result.addFirst(pop.data);
            }
        }
        return result;
    }
}
