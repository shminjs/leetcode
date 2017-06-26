package com.shminjs.leetcode.middle;

/**
 * Created by shimin on 2017/6/25.
 * 623
 * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The
 * root node is at depth 1.
 * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d - 1, crate two tree node with value
 * v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree
 * root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means that there is
 * no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's
 * left subtree.
 */
public class AddOneRow {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode t = new TreeNode(v);
            t.left = root;
            return t;
        }
        traverse(root, v, d, 1);
        return root;
    }

    private void traverse(TreeNode root, int v, int d, int depth) {
        if (root == null) return;
        if (depth == (d-1)) {
            TreeNode leftN = new TreeNode(v);
            leftN.left = root.left;
            root.left = leftN;
            TreeNode rightN = new TreeNode(v);
            rightN.right = root.right;
            root.right = rightN;
            return;
        }
        traverse(root.left, v, d, depth + 1);
        traverse(root.right, v, d, depth + 1);
    }
}
