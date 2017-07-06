package com.shminjs.leetcode.medium;

import java.util.LinkedList;

/**
 * Created by shimin on 2017/7/6.
 * 99
 * Two elements of a binary search tree (BST) are swapped by mistake
 * Recover the tree without changing its structure.
 */
public class RecoverTree {
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null;
        TreeNode pre = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                // 访问p
                if (pre != null) {
                    if (pre.val > p.val) {
                        if (first == null) {
                            first = pre;
                            second = p;
                        } else {
                            second = p;
                        }
                    }
                }
                pre = p;
                p = p.right;
            }
        }
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
