package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/14.
 * 26
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int start = 1;
        boolean flag = false;
        int i = 1;
        for (; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                if (!flag) {
                    start = i;
                    flag = true;
                }
            } else {
                if (flag) nums[start++] = nums[i];
            }
        }
        if (flag) return start;
        else return i;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicates().removeDuplicates(new int[]{1, 2}));
    }
}
