package com.shminjs.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/6/26.
 * 113
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 */
public class PathSum {
    private List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return result;
        traverse(root, sum, new LinkedList<>());
        return result;
    }

    private void traverse(TreeNode root, int sum, LinkedList<Integer> stack) {
        if (root == null) return;
        stack.push(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            List<Integer> tmp = new LinkedList<Integer>();
            for (Integer item : stack)
                tmp.add(0, item);
            result.add(tmp);
        }
        traverse(root.left, sum - root.val, stack);
        traverse(root.right, sum - root.val, stack);
        stack.pop();
    }
}
