package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/10.
 * 35
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if the were inserted order.
 */
public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int ind = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target > nums[i]) ind++;
        }
        return ind;
    }
}
