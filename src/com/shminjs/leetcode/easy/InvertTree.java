package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 226
 * Invert a binary ree
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        TreeNode inv = null;
        return invert(root);
    }

    private static TreeNode invert(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode inv = new TreeNode(root.val);
        inv.right = invert(root.left);
        inv.left = invert(root.right);
        return inv;
    }
}

/**
 * Definition for a binary tree node.
 */
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// }