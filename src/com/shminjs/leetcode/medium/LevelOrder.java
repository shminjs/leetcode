package com.shminjs.leetcode.middle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by shimin on 2017/6/26.
 * 102
 * Given a binary tree, return the level order traversal of its nodes' values.
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) return result;

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> container = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
                container.add(item.val);
            }
            result.add(container);
        }
        return result;
    }
}
