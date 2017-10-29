package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/10/12.
 * 669
 */
public class TrimBST {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return root;
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }else if (root.val < L) {
            return trimBST(root.right, L, R);
        } else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }
    }
}
