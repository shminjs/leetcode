package com.shminjs.leetcode.medium;

import java.util.LinkedList;

/**
 * Created by shimin on 2017/6/26.
 * 173
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 */
public class BSTIterator {
    private TreeNode p = null;
    private LinkedList<TreeNode> stack = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        p = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (p != null || !stack.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        p = stack.pop();
        TreeNode result = p;
        p = p.right;
        return result.val;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(2);
        t.left = new TreeNode(1);
        t.right = new TreeNode(4);
        t.right.left = new TreeNode(3);
        BSTIterator bsi = new BSTIterator(t);
        while (bsi.hasNext()) {
            System.out.println(bsi.next());
        }
    }
}
