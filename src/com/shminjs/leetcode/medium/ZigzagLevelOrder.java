package com.shminjs.leetcode.middle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by shimin on 2017/6/26.
 */
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root == null) return result;

        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> container = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
                if (flag) container.add(item.val);
                else container.add(0, item.val);
            }
            if (flag) flag = false;
            else flag = true;
            result.add(container);
        }
        return result;
    }
}
