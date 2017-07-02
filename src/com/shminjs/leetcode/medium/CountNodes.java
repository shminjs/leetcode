package com.shminjs.leetcode.middle;

/**
 * Created by shimin on 2017/6/27.
 * 222
 * Given a complete binary tree, count the number of nodes.
 */
public class CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int lHeight = getDepth(root.left);
        int rHeight = getDepth(root.right);

        if (lHeight == rHeight)
            return (1<<lHeight) + countNodes(root.right);
        else
            return (1<<rHeight) + countNodes(root.left);
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;

        int height = 0;
        while (root != null) {
            height++;
            root = root.left;
        }
        return height;
    }
}
