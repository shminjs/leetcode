package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/3.
 * 98
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 *  - The left subtree of a node contains only nodes with keys less than the node's key.
 *  - The right subtree of a node only contains only nodes with keys greater than node's key.
 *  - Both the left and right subtrees must also be binary search trees.
 */
public class IsValidBST {
    boolean flag = true;
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        traversal(root);
        return flag;
    }

    private void traversal(TreeNode root) {
        if (root == null) return;
        traversal(root.left);
        if (pre != null) {
            if (root.val < pre.val)
                flag = false;
        }
        pre = root;
        traversal(root.right);
    }
}
