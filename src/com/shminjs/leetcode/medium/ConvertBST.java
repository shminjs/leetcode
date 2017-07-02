package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/6/25.
 * 538
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key
 * plus sum of all keys greater than the original key in BST.
 */
public class ConvertBST {

    int prev = 0;
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.right);
        int tmp = root.val;
        root.val += prev;
        prev += tmp;
        traverse(root.left);
    }
}
