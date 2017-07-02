package com.shminjs.leetcode.middle;

/**
 * Created by shimin on 2017/6/25.
 * 337
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root". Besides
 * the root, each house has one and only one parent house. After a tour, the smart thief realized that "all house in this place forms a binary
 * tree". It will automatically contact the police if two-directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * 这一题有点意思。自己提交的东西和这里写的答案类似，但是没有这个简洁。此外，还需要更高效的做法。
 */
public class Rob {
    public int rob(TreeNode root) {
        if (root == null) return 0;

        int val = 0;
        if (root.left != null)
            val += rob(root.left.left) + rob(root.left.right);

        if (root.right != null)
            val += rob(root.right.right) + rob(root.right.left);

        return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }

    public static void main(String[] args) {

    }
}
