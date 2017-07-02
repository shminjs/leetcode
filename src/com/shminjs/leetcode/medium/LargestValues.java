package com.shminjs.leetcode.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by shimin on 2017/6/25.
 * 515
 * You need to find the largest value in each row of a binary tree.
 */
public class LargestValues {
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> results = new LinkedList<>();

        if (root == null) return results;

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            Integer max = null;
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
                if (max == null || max < item.val) max = item.val;
            }
            results.add(max);
        }
        return results;
    }
}
