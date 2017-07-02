package com.shminjs.leetcode.medium;

import java.util.LinkedList;

/**
 * Created by shimin on 2017/6/25.
 * 449
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be sorted in a file or memory
 * buffer, or transmitted across a network connect link to to reconstructed later in the same or another computer.
 * Design a algorithm to serialize de deserialize a binary search tree. The is no restriction on how your serialization/deserialization
 * algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to
 * the original tree subtree.
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) return sb.toString();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
                sb.append(item.val).append(' ');
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] datas = data.split(" ");
        TreeNode head = null;
        for (int i = 0; i < datas.length; i++) {
            head = generate(head, Integer.parseInt(datas[i]));
        }
        return head;
    }

    private TreeNode generate(TreeNode head, int val) {
        if (head == null) return new TreeNode(val);
        TreeNode s = head;
        TreeNode pre = null;
        while (s != null) {
            pre = s;
            if (val <= s.val) {
                s = s.left;
            } else {
                s = s.right;
            }
        }
        if (val <= pre.val) {
            pre.left = new TreeNode(val);
        } else {
            pre.right = new TreeNode(val);
        }
        return head;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(2);
        Codec codec = new Codec();
        String result = codec.serialize(t);
        TreeNode s = codec.deserialize(result);
        System.out.println(s.val);
        System.out.println(s.right.val);
        System.out.println(result);
    }
}
