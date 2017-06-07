package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 104
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        return depthFirst(root);
    }

    private static int depthFirst(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depthFirst(root.left);
        int right = depthFirst(root.right);
        return left > right ? left + 1 : right + 1;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
//         t.left.left = new TreeNode(4);
        t.left.right = new TreeNode(4);
        MaxDepth md = new MaxDepth();
        System.out.println(md.maxDepth(t));
    }
}

/**
 * Definition for a binary tree node.
 */
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
// }

