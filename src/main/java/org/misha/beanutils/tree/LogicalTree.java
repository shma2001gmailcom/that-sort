package org.misha.beanutils.tree;

import org.apache.log4j.Logger;
import org.misha.beanutils.tree.impl.NodeImpl;
import org.misha.beanutils.tree.impl.TreeImpl;

import java.util.LinkedList;
import java.util.List;
/**
 * Author: misha shevelin
 * Date: 1/28/12
 * Time: 7:16 PM
 */

/**
 * Given some correct org.misha.logical expression, this class produces the org.misha.logical.tree for
 * evaluating it. This class DOES NOT really evaluates the expression.
 * <p/>
 * THE RULES FOR BUILDING THE CORRECT EXPRESSION
 * <p/>
 * Let x, y, z be correct org.misha.logical expressions. Then the next (and similar)
 * org.misha.logical expressions also are correct.
 * <p/>
 * 0. Any letter is a correct expression.
 * <p/>
 * 1. The negation of x. Expression is true iff x is false: ((x) NOT);
 * <p/>
 * 2. At least one of x, y, z. Expression is true iff one of x, y, z is true:
 * ((x) (y) (z) OR);
 * <p/>
 * 3. All of x, y, z. Expression is true iff all x, y, z are true: ((x) (y) (z)
 * AND);
 * <p/>
 * Note that each parenthesis has a meaning.
 */
public final class LogicalTree {
    private static final Logger log = Logger.getLogger(LogicalTree.class);
    private final List<Character> list = new LinkedList<Character>();

    /**
     * @param expression the expression to build evaluation org.misha.logical.tree
     */
    public LogicalTree(final CharSequence expression) {
        for (int i = 0; i < expression.length(); ++i) {
            list.add(expression.charAt(i));
        }
    }

    private static String changeContent(String s, final char c) {
        if (c != ' ') {
            s += c;
        }
        return s;
    }

    private static Node<String> initNode(Node<String> node, final String s) {
        if (node != null) {
            node.setContent(s);
            node.setName(s);
            final Node<String> parent = node.getParent();
            if (parent != null) {
                node = parent;
            }
        }
        return node;
    }

    private static Node<String> createNode(final Node<String> parent, final String s) {
        final Node<String> node = new NodeImpl<String>(s);
        if (parent != null) {
            parent.addChild(node);
        }
        return node;
    }

    private boolean isCorrect() {
        int level = 0;
        for (final Character c : list) {
            level += c == ')' ? -1 : c == '(' ? 1 : 0;
        }
        return level == 0;
    }

    public Tree<String> makeTree() {
        if (!isCorrect()) {
            log.error("parenthesizes do not match, check input.");
            return null;
        }
        Node<String> node = null;
        Node<String> parent = null;
        String s = "";
        while (!list.isEmpty()) {
            final char c = list.remove(0);
            if (c == '(') {
                node = createNode(parent, s);
                parent = node;
            } else if (c == ')') {
                parent = initNode(parent, s);
                s = "";
            } else {
                s = changeContent(s, c);
            }
        }
        while (node != null && !node.isRoot()) {
            node = node.getParent();
        }
        return new TreeImpl<String>(node);
    }
}
