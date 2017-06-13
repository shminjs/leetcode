package com.shminjs.leetcode.easy;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/6/13.
 * 257
 * Given a binary tree, return all root-to-leaf paths.
 * For Example, given the following binary tree.
 *   1
 *  / \
 *  2  3
 *  /
 *  5
 *  1->2->5 1->3
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<TreeNode> stack = new ArrayList<>();
        List<String> results = new ArrayList<>();
        travel(root, stack, results);
        return results;
    }

    private void travel(TreeNode root, List<TreeNode> stack, List<String> results) {
        if (root == null) return;
        stack.add(root);
        if (root.right == null && root.left == null) {
            results.add(generate(stack));
        }
        travel(root.left, stack, results);
        travel(root.right, stack, results);
        stack.remove(stack.size()-1);
    }

    private String generate(List<TreeNode> stack) {
        StringBuilder sb = new StringBuilder();
        for (TreeNode item : stack) {
            sb.append(item.val);
            sb.append("->");
        }
        return sb.toString().substring(0, sb.length() - 2);
    }
}
