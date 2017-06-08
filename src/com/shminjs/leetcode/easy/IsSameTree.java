package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/8.
 * 100
 * Given two binary tree, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same values.
 */
public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return traver(p, q);
    }

    private boolean traver(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            boolean flag_middle = p.val == q.val ? true:false;
            boolean flag_left = traver(p.left, q.left);
            boolean flag_right = traver(p.right, q.right);
            return flag_left && flag_middle && flag_right;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(10);
        p.left = new TreeNode(5);
        p.right = new TreeNode(15);
        TreeNode q = new TreeNode(10);
        q.left = new TreeNode(5);
        IsSameTree ist = new IsSameTree();
        System.out.println(ist.isSameTree(p, q));
    }
}
