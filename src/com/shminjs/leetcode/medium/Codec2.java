package com.shminjs.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by shimin on 2017/7/3.
 * 297
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be sorted in a file or memory
 * buffer, or transmitted across a network connect link to to reconstructed later in the same or another computer.
 * Design a algorithm to serialize de deserialize a binary search tree. The is no restriction on how your serialization/deserialization
 * algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to
 * the original tree subtree.
 */
public class Codec2 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) return sb.toString();
        queue.offer(root);
        sb.append(root.val).append(' ');
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            StringBuilder tmp = new StringBuilder();
            int ind = 0;
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (item.left != null) {
                    queue.offer(item.left);
                    tmp.append(item.left.val).append(' ');
                    ind = tmp.length();
                } else {
                    tmp.append('#').append(' ');
                }
                if (item.right != null) {
                    queue.offer(item.right);
                    tmp.append(item.right.val).append(' ');
                    ind = tmp.length();
                } else {
                    tmp.append('#').append(' ');
                }
            }
            sb.append(tmp.toString());
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] datas = data.split(" ");
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode head = new TreeNode(Integer.parseInt(datas[0]));
        queue.offer(head);
        int ind = 1;
        while (!queue.isEmpty() && ind < datas.length) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (ind < datas.length && !datas[ind].equals("#")) {
                    item.left = new TreeNode(Integer.parseInt(datas[ind]));
                    queue.offer(item.left);
                }
                ind++;
                if (ind < datas.length && !datas[ind].equals("#")) {
                    item.right = new TreeNode(Integer.parseInt(datas[ind]));
                    queue.offer(item.right);
                }
                ind++;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.left.right = new TreeNode(3);
        Codec2 codec = new Codec2();
        String result = codec.serialize(t);
        System.out.println(result);
        TreeNode s = codec.deserialize(result);
        System.out.println(s.val);
        System.out.println(s.left.val);
        System.out.println(s.left.right.val);
        System.out.println(result.split(" ").length);
    }
}
