package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/6/26.
 * 105
 * Given preorder and inorder traversal of tree, construct the binary tree.
 */
public class BuildTree2 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length == 0 || preorder.length == 0) return null;
        return generate(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }

    public TreeNode generate(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend) {
        if (inend - instart + 1 >= 1) {
            int root = preorder[prestart];
            int ind = find(inorder, root, instart, inend);
            TreeNode head = new TreeNode(root);
            head.left = generate(inorder, instart, ind-1, preorder, prestart + 1, prestart + ind - instart);
            head.right = generate(inorder, ind + 1, inend, preorder, prestart + ind - instart + 1, preend);
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
}
