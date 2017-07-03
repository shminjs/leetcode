package com.shminjs.leetcode.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by shimin on 2017/7/3.
 * 236
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(3);
        t.left = new TreeNode(5);
        t.left.left = new TreeNode(6);
        t.left.right = new TreeNode(2);
        t.left.right.left = new TreeNode(7);
        t.left.right.right = new TreeNode(4);
        t.right = new TreeNode(1);
        t.right.left = new TreeNode(0);
        t.right.right = new TreeNode(8);

        LowestCommonAncestor lca = new LowestCommonAncestor();
        TreeNode p = lca.lowestCommonAncestor(t, t.left, t.left.right.right);
        System.out.println(p.val);
    }
}
