package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/13.
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every
 * node never differ by more than 1.
 */
public class IsBalanced {
    private boolean flag = true;

    public boolean isBalanced(TreeNode root) {
        depth(root);
        return flag;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        if (Math.abs(left-right) > 1) flag = false;
        return left > right ? left + 1: right + 1;
    }
}
