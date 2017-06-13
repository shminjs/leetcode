package com.shminjs.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by shimin on 2017/6/12.
 * 501
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 */
public class FindMode {
    public int[] findMode(TreeNode root) {
        ArrayList<Integer> results = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        int count = 1;
        int model = 1;
        // 遍历
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                // 访问item
                if (prev != null) {
                    if (prev.val == p.val) {
                        count++;
                    } else {
                        if (count == model) results.add(prev.val);
                        else if (count > model) {
                            results.clear();
                            results.add(prev.val);
                            model = count;
                        }
                        count = 1;
                    }
                }
                prev = p;
                // 结束访问
                p = p.right;
            }
            if (p == null && stack.isEmpty()) {
                // Do something
                if (count == model) results.add(prev.val);
                else if (count > model) {
                    results.clear();
                    results.add(prev.val);
                }
            }
        }
        // 结束遍历
        int[] array = new int[results.size()];
        int ind = 0;
        for (Integer item : results)
            array[ind++] = item;
        return array;
    }
    public static void main(String[] args) {
        TreeNode t = new TreeNode(6);
        t.left = new TreeNode(2);
        t.left.left = new TreeNode(0);
        t.left.left.right = new TreeNode(2);
        t.left.right = new TreeNode(4);
        t.left.right.right = new TreeNode(6);
        t.right = new TreeNode(8);
        t.right.left = new TreeNode(7);
        t.right.right = new TreeNode(9);
        FindMode fm = new FindMode();
        System.out.println(Arrays.toString(fm.findMode(t)));
    }
}
