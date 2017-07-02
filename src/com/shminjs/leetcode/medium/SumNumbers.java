package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/2.
 * 129
 * Given a binary tree containing digit from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represent the number 123.
 * Find the total sum of all root-to-leaf numbers.
 */
public class SumNumbers {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        traversal(root, 0);
        return sum;
    }

    private void traversal(TreeNode root, int num) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            num = num * 10 + root.val;
            sum += num;
            return;
        }
        traversal(root.left, num * 10 + root.val);
        traversal(root.right, num * 10 + root.val);
    }
}
