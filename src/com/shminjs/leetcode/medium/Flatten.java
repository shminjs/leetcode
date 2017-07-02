package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/2.
 * 114
 * Given a binary tree, flatten it to a linked in-place.
 */
public class Flatten {
    TreeNode p = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        p = root;
        traversal(left);
        traversal(right);
    }

    private void traversal(TreeNode root) {
        if (root == null) return;
        p.left = null;
        p.right = root;
        p = p.right;
        TreeNode left = root.left;
        TreeNode right = root.right;
        traversal(left);
        traversal(right);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.left.left = new TreeNode(5);
        Flatten f = new Flatten();
        f.flatten(t);
        while (t!=null) {
            System.out.println(t.val);
            t = t.right;
        }
    }
}
