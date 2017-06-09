package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/9.
 * 543
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of binary tree is the length of the
 * longest path between any two nodes in a tree. This path man or may not pass through the root.
 */
public class DiameterOfBinaryTree {
    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        posttraversal(root);
        return diameter;
    }

    private int posttraversal(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height_left = posttraversal(root.left);
        int height_right = posttraversal(root.right);
        if (height_left + height_right > diameter) diameter = height_left + height_right;
        return height_left > height_right ? height_left + 1 : height_right + 1;
    }
}
