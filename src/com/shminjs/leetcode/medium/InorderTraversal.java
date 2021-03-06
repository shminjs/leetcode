package com.shminjs.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/6/25.
 * 94
 * Given a binary tree, return
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        if (root == null) return result;

        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                result.add(p.val);
                p = p.right;
            }
        }
        return result;
    }
}
