package com.shminjs.leetcode.medium;

import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/6/25.
 * 144
 * Given a binary tree, return the preorder traversal of its nodes' value
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        if (root == null) return result;

        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                result.add(p.val);
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
        return result;
    }

    public int maxSum(TreeNode root) {
        LinkedList<Element> queue = new LinkedList<>();
        if (root == null) return 0;

        TreeNode p = root;
        queue.offer(new Element(p, p.val));
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                Element elem = queue.poll();
                if (elem.item.left != null) {
                    queue.offer(new Element(elem.item.left, elem.item.left.val + elem.sum));
                }
                if (elem.item.right != null) {
                    queue.offer(new Element(elem.item.right, elem.item.right.val + elem.sum));
                }
                max = Math.max(max, elem.sum);
            }
        }
        return max;
    }

    class Element {
        TreeNode item;
        int sum;

        public Element(TreeNode item, int sum) {
            this.item = item;
            this.sum = sum;
        }
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(3);
        t.left = new TreeNode(4);
        t.left.right = new TreeNode(20);
        t.right = new TreeNode(2);
        t.right.left = new TreeNode(10);
        t.right.left.left = new TreeNode(30);
        System.out.println(new PreorderTraversal().maxSum(t));
    }
}
