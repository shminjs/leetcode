package com.shminjs.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shimin on 2017/10/12.
 * 653
 */
public class FindTarget {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findTarget(root, k, set);
    }

    private boolean findTarget(TreeNode root, int k, Set<Integer> set) {
        if (root == null) return false;
        boolean left = findTarget(root.left, k, set);
        if (left) return left;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        boolean right = findTarget(root.right, k, set);
        return right;
    }
}
