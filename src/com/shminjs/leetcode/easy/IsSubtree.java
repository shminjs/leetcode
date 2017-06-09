package com.shminjs.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimin on 2017/6/9.
 * Given two non-empty binary tree s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A
 * subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as subtree of itself.
 */
public class IsSubtree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        ArrayList<TreeNode> results = new ArrayList<>();
        for (TreeNode item : results)
            if (isIdentical(item, t)) return true;
        return false;
    }

    private boolean isIdentical(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s != null && t != null) {
            return s.val == t.val && isIdentical(s.left, t.left) && isIdentical(s.right, t.right);
        } else {
            return false;
        }
    }

    private void findRoot(TreeNode s, int val, List<TreeNode> results) {
        if (s == null) return;
        if (s.val == val) results.add(s);
        findRoot(s.right, val, results);
        findRoot(s.left, val, results);
    }
}
