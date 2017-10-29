package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/10/1.
 * 687
 */
public class LongestUnivaluePath {
    int result = 0;
    public int longestUnivaluePath(TreeNode root) {
        traverse(root);
        return result;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        int left = pathThroughThisNode(root.left, root.val);
        int right = pathThroughThisNode(root.right, root.val);
        result = Math.max(result, left + right);
        traverse(root.left);
        traverse(root.right);
    }

    private int pathThroughThisNode(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        if (root.val == val) {
            int left = pathThroughThisNode(root.left, val) + 1;
            int right = pathThroughThisNode(root.right, val) + 1;
            return Math.max(left, right);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(5);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.right = new TreeNode(4);
        System.out.println(new LongestUnivaluePath().longestUnivaluePath(treeNode));
    }
}
