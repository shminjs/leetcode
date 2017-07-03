package com.shminjs.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/7/3.
 * 145
 */
public class PostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        if (root == null) return result;

        TreeNode p = root;
        TreeNode pre = null;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.peek();
                if (p.right == null || p.right == pre) {
                    result.add(p.val);
                    pre = p;
                    stack.pop();
                    p = null;
                } else {
                    p = p.right;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(3);
        t.left.left = new TreeNode(6);
        t.left.left.right = new TreeNode(8);
        t.left.right = new TreeNode(7);
        t.left.right.left = new TreeNode(4);
        t.right = new TreeNode(5);
        PostorderTraversal pt = new PostorderTraversal();
        List<Integer> result = pt.postorderTraversal(t);
        System.out.println(result.toString());
    }
}
