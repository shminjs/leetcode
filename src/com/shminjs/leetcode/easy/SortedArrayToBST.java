package com.shminjs.leetcode.easy;

import sun.reflect.generics.tree.Tree;

/**
 * Created by shimin on 2017/6/9.
 * 108
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return generateBST(nums, 0, nums.length - 1);
    }

    private TreeNode generateBST(int[] nums, int lo, int hi) {
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            TreeNode t = new TreeNode(nums[mid]);
            t.left = generateBST(nums, lo, mid - 1);
            t.right = generateBST(nums, mid + 1, hi);
            return t;
        } else {
            return null;
        }
    }
}
