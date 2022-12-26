package org.misha.luxoft;

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * <p>
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 * <p>
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
 * connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass
 * through the root.
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * <p>
 * a letCode "hard" problem
 */
public class Solution {
    private static int max(final int leftVal, final int rightVal) {
        return leftVal > rightVal ? leftVal : rightVal;
    }

    public int maxPathSum(TreeNode root) {
        final TreeNode left = root.left;
        final TreeNode right = root.right;
        if (root.val < 0) {
            if (left == null && right == null) return root.val;
            if (left == null) {
                return oneLegVal(root, right);
            }
            if (right == null) {
                return oneLegVal(root, left);
            }
            while (!left.isPreLeaf() && !right.isPreLeaf()) {
                root.trim();
            }
            final TreeNode leftLeft = left.left;
            final int leftVal = leftLeft == null ? left.val : leftLeft.val + left.val + left.right.val;
            final TreeNode rightLeft = right.left;
            final int rightVal = rightLeft == null ? right.val : rightLeft.val + right.val + right.right.val;
            return max(leftVal, rightVal);
        }
        return commonCaseVal(root, left, right);
    }

    private int commonCaseVal(final TreeNode root,
                              final TreeNode left,
                              final TreeNode right
    ) {
        while (!root.isPreLeaf()) {
            root.trim();
        }
        final int leftVal = left == null ? 0 : root.left.val;
        final int rightVal = right == null ? 0 : root.right.val;
        return leftVal + root.val + rightVal;
    }

    private int oneLegVal(final TreeNode root, final TreeNode oneLeg) {
        while (!oneLeg.isPreLeaf()) {
            root.trim();
        }
        final TreeNode rightLeft = oneLeg.left;
        return rightLeft == null ? oneLeg.val : rightLeft.val + oneLeg.val + oneLeg.right.val;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        boolean isPreLeaf() {
            return (left == null || left.isLeaf()) && (right == null || right.isLeaf());
        }

        void trim() {
            for (TreeNode node : toTrim()) {
                final TreeNode nodeLeft = node.left;
                final int leftVal = nodeLeft == null ? 0 : nodeLeft.val;
                final TreeNode nodeRight = node.right;
                final int rightVal = nodeRight == null ? 0 : nodeRight.val;
                node.val += max(leftVal, rightVal);
                node.left = null;
                node.right = null;
            }
        }

        List<TreeNode> toTrim() {
            final LinkedList<TreeNode> result = new LinkedList<TreeNode>() {
                @Override
                public TreeNode pop() {
                    final TreeNode pop = super.pop();
                    final TreeNode popLeft = pop.left;
                    manage(popLeft);
                    final TreeNode popRight = pop.right;
                    manage(popRight);
                    return null;
                }

                private void manage(final TreeNode node) {
                    if (node != null) {
                        if (node.isPreLeaf()) {
                            super.add(node);
                        } else if (!node.isLeaf()) {
                            super.push(node);
                        }
                    }
                }
            };
            result.add(this);
            while (!result.peek().isPreLeaf()) {
                result.pop();
            }
            return result;
        }

        @Override
        public String toString() {
            if (isLeaf()) {
                return String.valueOf(val);
            }
            return val + "->[" + left + "," + right + "]";
        }
    }
}
