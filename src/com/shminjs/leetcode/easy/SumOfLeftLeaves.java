package com.shminjs.leetcode.easy;

import sun.reflect.generics.tree.Tree;

/**
 * Created by shimin on 2017/6/7.
 * 404
 * Find the sum of all left leaves in a given binary tree.
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeaves(root, false);
    }

    private int sumOfLeaves(TreeNode root, boolean side) {
        if (root == null) return 0;
        if (side && root.left == null && root.right == null) return root.val;
        return sumOfLeaves(root.left, true) + sumOfLeaves(root.right, false);
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
