package com.shminjs.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/6/25.
 * 144
 * Given a binary tree, return the preorder traversal of its nodes' value
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        if (root == null) return result;

        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                result.add(p.val);
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
        return result;
    }
}
