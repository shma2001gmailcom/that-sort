package org.misha.luxoft;

public class TreeMaxPathSum {

    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }

    private int maxPathSumHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSum = maxPathSumHelper(node.left);
        int rightSum = maxPathSumHelper(node.right);
        // check if the maximum path sum goes through the current node
        int pathSum = node.val;
        if (leftSum > 0) {
            pathSum += leftSum;
        }
        if (rightSum > 0) {
            pathSum += rightSum;
        }
        maxSum = Math.max(maxSum, pathSum);
        // return the maximum sum of a path starting from the current node
        return Math.max(node.val, Math.max(node.val + leftSum, node.val + rightSum));
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
    }
}

