package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/9.
 * Given an array containing n distinct numbers taken from 0, 1, 2, ... n, find the one that is missing from the array.
 * For example:
 * Given nums = [0, 1, 3] return 2.
 *
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int result = nums.length + 1;
        for (int i = 0; i < nums.length; i++)
            nums[i] += 1;
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i]) < result) {
                int ind = Math.abs(nums[i]);
                nums[ind-1] = -nums[ind-1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return i;
        }
        return result - 1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0};
        MissingNumber mn = new MissingNumber();
        System.out.println(mn.missingNumber(nums));
    }
}
