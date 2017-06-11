package com.shminjs.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by shimin on 2017/6/10.
 * 107
 * Given a binary tree, return the bottom-up level order traversal of its nodes'vales. (ie, from left to right, level from leaf to root).
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);
        boolean flag = true;
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            if (flag) {
                // 表明queue1不为空
                addToList(queue1, queue2, results);
                flag = false;
            } else {
                // 表明queue2不为空
                addToList(queue2, queue1, results);
                flag = true;
            }
        }
        int N = results.size();
        for (int i = 0; i < N / 2; i++) {
            List<Integer> temp = results.get(i);
            results.set(i, results.get(N-i-1));
            results.set(N-i-1, temp);
        }
        return results;
    }

    private void addToList(LinkedList<TreeNode> queue1, LinkedList<TreeNode> queue2, List<List<Integer>> results) {
        ArrayList<Integer> temp = new ArrayList<>();
        while (!queue1.isEmpty()) {
            TreeNode item = queue1.poll();
            temp.add(item.val);
            if (item.left != null) queue2.offer(item.left);
            if (item.right != null) queue2.offer(item.right);
        }
        results.add(temp);
    }

    public static void main(String[] args) {
        LevelOrderBottom lob = new LevelOrderBottom();
        TreeNode t = null;
        List<List<Integer>> results = lob.levelOrderBottom(t);
    }
}
