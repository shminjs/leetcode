package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/24.
 * 303
 * Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
 */
public class NumArray {
    private int[] results;

    public NumArray(int[] nums) {
        results = new int[nums.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = (i - 1 >= 0 ? results[i-1]:0) + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return results[j];
        } else {
            return results[j] - results[i-1];
        }
    }
}
