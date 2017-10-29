package com.shminjs.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shimin on 2017/10/13.
 * 662
 */
public class WidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 1));
        int res = 1;
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            int start = 0, end = 0;
            for (int i = 0; i < levelNum; i++) {
                Node item = queue.poll();
                if (i == 0) {
                    start = item.no;
                }
                if (i == levelNum-1) {
                    end = item.no;
                }
                if (item.item.left != null) {
                    queue.offer(new Node(item.item.left, item.no * 2));
                }
                if (item.item.right != null) {
                    queue.offer(new Node(item.item.right, item.no * 2 + 1));
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}

class Node {
    TreeNode item;
    int no;

    public Node(TreeNode item, int no) {
        this.item = item;
        this.no = no;
    }
}
