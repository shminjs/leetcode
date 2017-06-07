package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 530
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any tow nodes.
 * 中序遍历
 */
public class GetMinimumDifference {
    int minDiff = Integer.MAX_VALUE;
    TreeNode prev;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null) minDiff = Math.min(minDiff, root.val - prev.val);
        prev = root;
        inorder(root.right);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(236);
        t.left = new TreeNode(104);
        t.left.right = new TreeNode(227);
        t.right = new TreeNode(701);
        t.right.right = new TreeNode(911);
        GetMinimumDifference gd = new GetMinimumDifference();
        System.out.println(gd.getMinimumDifference(t));
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
