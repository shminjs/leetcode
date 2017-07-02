package com.shminjs.leetcode.middle;

import java.util.*;

/**
 * Created by shimin on 2017/6/25.
 * 508
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum
 * of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum
 * value? if there is a tie, return all the values with the highest frequency in any order.
 */
public class FindFrequentTreeSum {
    int count = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        traverse(root, map, result);
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) res[i] = result.get(i);
        return res;
    }

    private int traverse(TreeNode root, Map<Integer, Integer> map, List<Integer> result) {
        if (root == null) return 0;
        int left = traverse(root.left, map, result);
        int right = traverse(root.right, map, result);
        int val = left + right + root.val;
        if (map.containsKey(val)) {
            map.put(val, map.get(val) + 1);
        } else {
            map.put(val, 1);
        }
        if (map.get(val) > count) {
            result.clear();
            result.add(val);
            count = map.get(val);
        } else if (map.get(val) == count) {
            result.add(val);
        }
        return val;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(5);
        t.left = new TreeNode(2);
        t.right = new TreeNode(-3);
        System.out.println(Arrays.toString(new FindFrequentTreeSum().findFrequentTreeSum(t)));
    }
}
