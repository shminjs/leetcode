package com.shminjs.leetcode.medium;

import java.util.*;

/**
 * Created by shimin on 2017/7/6.
 * 95
 * Given an integer n, generate all structurally unique BST's (binary search tree) that store values 1...n
 * For example,
 * Given n = 3, your program should return all 5 unique BST's show below.
 */
public class GenerateTrees {
    public List<TreeNode> generateTrees(int n) {
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        List<TreeNode> result0 = new LinkedList<>();
        if (n == 0) return result0;
        result0.add(null);
        map.put(0, result0);
        List<TreeNode> result1 = new LinkedList<>();
        result1.add(new TreeNode(1));
        map.put(1, result1);
        for (int i = 2; i <= n; i++) {
            List<TreeNode> result = new LinkedList<>();
            for (int j = 1; j <= i; j++) {
                for (TreeNode left : map.get(j-1)) {
                    for (TreeNode right : map.get(i-j)) {
                        TreeNode root = new TreeNode(j);
                        root.left = left;
                        root.right = right;
                        result.add(root);
                    }
                }
            }
            map.put(i, result);
        }
        List<TreeNode> res = new LinkedList<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i+1;
        List<TreeNode> tmp = map.get(n);
        for (TreeNode item : tmp) {
            res.add(generate(item, arr));
        }
        return res;
    }

    private TreeNode generate(TreeNode root, int[] arr) {
        if (root == null) return null;
        TreeNode head = new TreeNode(arr[root.val-1]);
        int[] left = new int[root.val - 1];
        System.arraycopy(arr, 0, left, 0, left.length);
        head.left = generate(root.left, left);
        int[] right = new int[arr.length - root.val];
        System.arraycopy(arr, root.val, right, 0, right.length);
        head.right = generate(root.right, right);
        return head;
    }

    public static void main(String[] args) {
        GenerateTrees gt = new GenerateTrees();
        List<TreeNode> re = gt.generateTrees(3);
        int[] array = new int[] {1, 3, 4};
        int[] tmp = new int[3];
        System.arraycopy(array, 0, tmp, 0, 3);
        System.out.println(Arrays.toString(tmp));
    }
}
