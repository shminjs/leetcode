package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/15.
 * Given a binary tree, find its minimum depth.
 * The nimimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinDepth {
    private int minDepth = Integer.MAX_VALUE;
    private int depth = 0;

    public int minDepth(TreeNode root) {
        travel(root);
        return minDepth;
    }

    private void travel(TreeNode root) {
        if (root == null) {
            if (depth < minDepth) minDepth = depth;
            return;
        }
        depth = depth + 1;
        travel(root.left);
        travel(root.right);
        depth = depth - 1;
    }
}
