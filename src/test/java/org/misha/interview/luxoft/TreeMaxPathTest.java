package org.misha.interview.luxoft;

import org.junit.Test;
import org.misha.luxoft.TreeMaxPath;
import org.misha.luxoft.TreeMaxPath.TreeNode;

import static org.junit.Assert.assertEquals;

/*
 *
 *                             -1
 *                            /  \
 *                           /    \
 *                          5      4------7
 *                         / \      \
 *                        /   \      \
 *                       1     1      3-----2
 *                            / \      \
 *                           /   \      \
 *                          3     2      4
 *                               / \
 *                              /   \
 *                             1     4
 */

public class TreeMaxPathTest {
    private final TreeMaxPath.TreeNode l1 = new TreeMaxPath.TreeNode(1);
    private final TreeMaxPath.TreeNode l2 = new TreeMaxPath.TreeNode(3);
    private final TreeMaxPath.TreeNode l3 = new TreeMaxPath.TreeNode(1);
    private final TreeMaxPath.TreeNode l4 = new TreeMaxPath.TreeNode(4);
    private final TreeMaxPath.TreeNode l5 = new TreeMaxPath.TreeNode(4);
    private final TreeMaxPath.TreeNode l6 = new TreeNode(2);
    private final TreeNode l7 = new TreeNode(7);
    private final TreeNode n1 = new TreeNode(2, l3, l4);
    private final TreeNode n3 = new TreeNode(1, l2, n1);
    private final TreeNode n4 = new TreeNode(5, l1, n3);
    private final TreeNode n2 = new TreeNode(3, l5, l6);
    private final TreeNode n5 = new TreeNode(4, n2, l7);
    private final TreeNode root = new TreeNode(1, n4, n5);

    @Test
    public void maxPathSum() throws Exception {
        commonCaseNegativeRoot();
        singleton();
        oneLeg();
        preLeaf();
        negativeRoot();
        commonCase(24, new TreeMaxPath().maxPathSum(root));
    }

    private void commonCase(final int expected, final int actual) {
        assertEquals(expected, actual);
    }

    private void commonCaseNegativeRoot() {
        TreeNode root = new TreeNode(-1, n4, n5);
        commonCase(18, new TreeMaxPath().maxPathSum(root));
    }

    private void negativeRoot() {
        final TreeNode root;
        root = new TreeNode(-1, new TreeNode(1), new TreeNode(1));
        commonCase(1, new TreeMaxPath().maxPathSum(root));
    }

    private void preLeaf() {
        final TreeNode root;
        root = new TreeNode(1, new TreeNode(1), new TreeNode(1));
        commonCase(3, new TreeMaxPath().maxPathSum(root));
    }

    private void oneLeg() {
        final TreeNode root;
        root = new TreeNode(1, new TreeNode(1), null);
        commonCase(2, new TreeMaxPath().maxPathSum(root));
    }

    private void singleton() {
        final TreeNode root;
        root = new TreeNode(1);
        commonCase(1, new TreeMaxPath().maxPathSum(root));
    }
}
