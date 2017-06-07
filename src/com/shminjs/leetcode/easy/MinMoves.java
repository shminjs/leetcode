package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/7.
 * 453
 * Given a non-empty integer array os size n, find the minimum number of moves required to make all array elements equal, where a move is increment n - 1
 * elements by 1.
 */
public class MinMoves {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) min = nums[i];
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += nums[i] - min;
        }
        return result;
    }
}
