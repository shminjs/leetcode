package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/10.
 * 112
 * Given a binary tree and a sum, determine is the tree has a root to lear path such that adding up all values along the path equals the given sum.
 */
public class PathSum1 {
    private boolean flag = false;
    private int result = 0;

    public boolean hasPathSum(TreeNode root, int sum) {
        traversel(root, sum);
        return flag;
    }

    private void traversel(TreeNode root, int sum) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            // 叶子节点
            result += root.val;
            if (result == sum) flag = true;
            result -= root.val;
        } else {
            result += root.val;
            traversel(root.left, sum);
            traversel(root.right, sum);
            result -= root.val;
        }
    }

    // 简洁
    public boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null && sum - root.val == 0) return true;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
