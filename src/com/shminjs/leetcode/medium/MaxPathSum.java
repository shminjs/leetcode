package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/6.
 * 124
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connection.
 * The path contain at least one node and does not to go through the root.
 */
public class MaxPathSum {
    Integer maxsofar = null;
    Integer maxendinghere = null;
    public int maxPathSum(TreeNode root) {
        traversal(root);
        return maxsofar;
    }

    private int traversal(TreeNode root) {
        if (root == null) return 0;
        int left = traversal(root.left);
        int right = traversal(root.right);
        if (maxendinghere == null && maxsofar == null) {
            maxendinghere = root.val;
            maxsofar = root.val;
        } else {
            maxendinghere = Math.max(Math.max(left, right) + root.val, root.val);
            maxsofar = Math.max(Math.max(maxendinghere, maxsofar), left + right + root.val);
        }
        return maxendinghere;
    }

    public static void main(String[] args) {
        MaxPathSum mps = new MaxPathSum();
        TreeNode t = new TreeNode(2);
        t.left = new TreeNode(-1);
//        t.right = new TreeNode(3);
        int result = mps.maxPathSum(t);
        System.out.println(result);
    }
}
