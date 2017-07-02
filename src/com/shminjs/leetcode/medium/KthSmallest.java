package com.shminjs.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/6/25.
 * Given a binary search tree, write a function k-th-smallest to find the k-th smallest element in it.

 */
public class KthSmallest {
    int val;
    public int kthSmallest(TreeNode root, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        traverse(root, map, k);
        return val;
    }

    private void traverse(TreeNode root, Map<Integer, Integer> map, int k) {
        if (root == null) return;
        traverse(root.left, map, k);
        if (!map.containsKey(root.val)) {
            map.put(root.val, 1);
            if (map.size() == k) {
                val = root.val;
                return;
            }
        }
        traverse(root.right, map, k);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(2);
        t.left = new TreeNode(1);
        System.out.println(new KthSmallest().kthSmallest(t, 1));
    }
}
