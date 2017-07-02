package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/6/26.
 * 106
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 */
public class BuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        return generate(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode generate(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend) {
        if (inend - instart + 1 >= 1) {
            int root = postorder[postend];
            int ind = find(inorder, root, instart, inend);
            TreeNode head = new TreeNode(root);
            head.left = generate(inorder, instart, ind-1, postorder, poststart, poststart + ind - instart-1);
            head.right = generate(inorder, ind + 1, inend, postorder, poststart + ind - instart, postend-1);
            return head;
        } else {
            return null;
        }
    }

    private int find(int[] nums, int val, int start, int end) {
        int ind = -1;
        for (int i = start; i <= end; i++) {
            if (nums[i] == val) {
                ind = i;
                break;
            }
        }
        return ind;
    }

    public static void main(String[] args) {
        BuildTree bt = new BuildTree();
        TreeNode t = bt.buildTree(new int[]{2, 1}, new int[]{2, 1});
        System.out.println(t.val);
        System.out.println(t.left.val);
    }
}
