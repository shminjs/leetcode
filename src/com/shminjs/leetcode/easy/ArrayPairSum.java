package com.shminjs.leetcode.easy;

import java.util.Arrays;
/**
 * Created by shimin on 2017/6/4.
 * 561
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer, say(a1,b1),(a2,b2),...,(an,bn) which makes sum
 * of min(ai,bi) for all i from 1 to n as large as possible.
 */
public class ArrayPairSum {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i = i+2) {
            sum += nums[i];
        }
        return sum;
    }
}
