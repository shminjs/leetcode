package com.shminjs.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shimin on 2017/6/25.
 * 513
 * Given a binary tree, find the leftmost value in the last row of the tree.
 */
public class FindBottomLeftValue {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        Integer leftmost = root.val;
        queue.offer(root);
        while (!queue.isEmpty()) {
           int levelNum = queue.size();
           boolean flag = false;
           for (int i = 0; i < levelNum; i++) {
               if (queue.peek().left != null) {
                   queue.offer(queue.peek().left);
               }
               if (queue.peek().right != null) {
                   queue.offer(queue.peek().right);
               }
               int val = queue.poll().val;
               if (!flag) {
                   leftmost = val;
                   flag = true;
               }
           }
        }
        return leftmost;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(3);
        t.left.left = new TreeNode(4);
        t.right.left = new TreeNode(5);
        t.right.left.left = new TreeNode(7);
        t.right.right = new TreeNode(6);
        System.out.println(new FindBottomLeftValue().findBottomLeftValue(t));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {val = x;}
}
