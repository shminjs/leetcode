package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/7.
 * 563
 * Given a binary tree, return the tilt of the whole tree.
 * The tilt of a tree node is defined as the absolute difference between the sum of al left subtree node values and the sum of all right subtree node
 * values. Null node has tilt 0.
 * The tilt of the whole tree is defined as the sum of all nodes's tilt.
 */
public class FindTilt {
    private int sum = 0;

    public int findTilt(TreeNode root) {
        lrd(root);
        return sum;
    }

    private int lrd(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = lrd(root.left);
        int right = lrd(root.right);
        int middle = Math.abs(left - right);
        sum += middle;
        return left + right + root.val;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.left.left = new TreeNode(4);
        t.right = new TreeNode(3);
        t.right.left = new TreeNode(5);
        FindTilt ft = new FindTilt();
        System.out.println(ft.findTilt(t));
    }
}
