package com.shminjs.leetcode.medium;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/8/30.
 * 655
 */
public class PrintTree {
    public List<List<String>> printTree(TreeNode root) {
        int h = height(root);
        int k = 0;
        for (int i = 1; i < h; i++) {
            k = 2 * k + 1;
        }
        return generate(root, k);
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        else return Math.max(height(root.left), height(root.right)) + 1;
    }

    private List<List<String>> generate(TreeNode root, int h) {
        if (h == 0) {
            List<List<String>> res = new LinkedList();
            if (root == null) {
                List<String> tmp = new LinkedList();
                tmp.add("");
                res.add(tmp);
                return res;
            } else {
                List<String> tmp = new LinkedList();
                tmp.add(Integer.toString(root.val));
                res.add(tmp);
                return res;
            }
        } else {
            List<String> head = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                head.add("");
            }
            if (root != null) {
                head.add(Integer.toString(root.val));
            } else {
                head.add("");
            }
            for (int i = 0; i < h; i++) {
                head.add("");
            }
            List<List<String>> left = generate(root.left, (h - 1) / 2);
            List<List<String>> right = generate(root.right, (h - 1) / 2);
            List<List<String>> res = new LinkedList<>();
            res.add(head);
            Iterator<List<String>> lI = left.iterator();
            Iterator<List<String>> rI = right.iterator();
            while (lI.hasNext() && rI.hasNext()) {
                List<String> tmp = new LinkedList<>();
                tmp.addAll(lI.next());
                tmp.add("");
                tmp.addAll(rI.next());
                res.add(tmp);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(5);
        t.left.left = new TreeNode(3);
        t.left.left.left = new TreeNode(4);
        new PrintTree().printTree(t);
    }
}

//public List<List<String>> printTree(TreeNode root) {
//    List<List<String>> res = new ArrayList<>();
//    if (root == null) return res;
//    int width = getWidth(root);
//    int height = getHeight(root);
//    for (int i = 0; i < height; i++) {
//        List<String> sub = new ArrayList<>();
//        for (int j = 0; j < width; j++) {
//            sub.add("");
//        }
//        res.add(sub);
//    }
//    traverse(root, 0, 0, width, res);
//    return res;
//}
//
//private void traverse(TreeNode root, int depth, int l, int h, List<List<String>> res) {
//    if (root == null) return;
//    int ind = (l + h) / 2;
//    res.get(depth).set(ind, "" + root.val);
//    traverse(root.left, depth + 1, l, ind, res);
//    traverse(root.right, depth + 1, ind + 1, h, res);
//}
//
//private int getWidth(TreeNode root) {
//    if (root == null) return 0;
//    else if (root.left == null && root.right == null) return 1;
//    else {
//        return Math.max(getWidth(root.left), getWidth(root.right)) * 2 + 1;
//    }
//}
//
//private int getHeight(TreeNode root) {
//    if (root == null) return 0;
//    return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
//}
