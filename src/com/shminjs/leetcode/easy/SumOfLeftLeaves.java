package com.shminjs.leetcode.easy;

import sun.reflect.generics.tree.Tree;

/**
 * Created by shimin on 2017/6/7.
 * 404
 * Find the sum of all left leaves in a given binary tree.
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        TreeNode prev = null;
        return calculateLeft(root, prev);
    }

    private int calculateLeft(TreeNode root, TreeNode prev) {
        // 感觉需要后序遍历
        if (root == null) {
            return 0;
        }
        int left = calculateLeft(root.left, root);
        int right = calculateLeft(root.right, root);
        if (prev != null && prev.left == root && root.left == null && root.right == null) {
            return root.val;
        } else {
            return left + right;
        }
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(3);
        t.left = new TreeNode(9);
        t.right = new TreeNode(20);
        t.right.left = new TreeNode(15);
        t.right.right = new TreeNode(7);
        SumOfLeftLeaves sll = new SumOfLeftLeaves();
        System.out.println(sll.sumOfLeftLeaves(t));
    }
}
