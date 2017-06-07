package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 485
 * Given a binary array, find the maximum of consecutive 1s in this array.
 */
public class FindMaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                if (count > max) max = count;
                count = 0;
            }
        }
        if (count > max) max = count;
        return max;
    }
}
