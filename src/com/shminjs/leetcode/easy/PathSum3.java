package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/10.
 * 437
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1000 nodes and the values are in the range -1000000 to 1000000.
 */
public class PathSum3 {
    private int result = 0;

    public int pathSum(TreeNode root, int sum) {
        travel(root, sum);
        return result;
    }

    private void travel(TreeNode root, int sum) {
        if (root == null) return;
        hasPathSum1(root, sum);
        travel(root.left, sum);
        travel(root.right, sum);
    }

    // 简洁
    public void hasPathSum1(TreeNode root, int sum) {
        if(root == null) return;

        if(sum - root.val == 0) result++;

        hasPathSum1(root.left, sum - root.val);
        hasPathSum1(root.right, sum - root.val);
    }

    public static void main(String[] args) {
//        TreeNode t = new TreeNode(10);
//        t.left = new TreeNode(5);
//        t.left.left = new TreeNode(3);
//        t.left.left.left = new TreeNode(3);
//        t.left.left.right = new TreeNode(-2);
//        t.left.right = new TreeNode(2);
//        t.left.right.right = new TreeNode(1);
//        t.right = new TreeNode(-3);
//        t.right.right = new TreeNode(11);
//        TreeNode t = new TreeNode(5);
//        t.left = new TreeNode(4);
//        t.left.left = new TreeNode(11);
//        t.left.left.left = new TreeNode(7);
//        t.left.left.right = new TreeNode(2);
//        t.right = new TreeNode(8);
//        t.right.left = new TreeNode(13);
//        t.right.right = new TreeNode(4);
//        t.right.right.left = new TreeNode(5);
//        t.right.right.right = new TreeNode(1);
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(2);
        t.right.right = new TreeNode(3);
//        t.right.right.right = new TreeNode(4);
//        t.right.right.right.right = new TreeNode(5);
        PathSum3 ps3 = new PathSum3();
        System.out.println(ps3.pathSum(t, 3));
    }
}
