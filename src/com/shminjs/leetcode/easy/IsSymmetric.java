package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/12.
 * 101
 * Given a binary tree, check whether it is mirror of itself (ie, symmetric around its center)
 * For example, this binary tree [1, 2, 2, 3, 4, 4 3] is symmetric.
 */
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return traver(root.left, root.right);
    }

    private boolean traver(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) {
            return false;
        } else {
            return traver(p.left, q.right) && traver(p.right, q.left);
        }
    }
}
