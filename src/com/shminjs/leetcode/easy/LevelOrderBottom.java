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
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> tmp = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                tmp.add(item.val);
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
            }
            res.add(0, tmp);
        }
        return res;
    }
}
