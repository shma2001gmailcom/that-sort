package org.misha.pyramid;

import java.util.ArrayList;
import java.util.List;

public class FromChildToParent {
    private final Node<Integer> root;
    private final List<Node<Integer>> bestPath;

    public FromChildToParent(Node<Integer> root) {
        this.root = root;
        bestPath = new ArrayList<>();
    }
}
