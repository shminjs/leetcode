package com.shminjs.leetcode.middle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by shimin on 2017/6/26.
 * 199
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered form top
 * to bottom.
 */
public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new LinkedList<>();

        if (root == null) return result;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
                if (i == levelNum - 1) {
                    result.add(item.val);
                }
            }
        }
        return result;
    }
}
