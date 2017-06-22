package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/21.
 * 581
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order. Then
 * the whole array will be in ascending order, too.
 * You need th find the shortest such subarray and output its length.
 *
 * 答案值得一看
 */
public class FindUnsortedSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int[] tmps = Arrays.copyOf(nums, nums.length);
        Arrays.sort(tmps);
        int start = 0, end = -1;
        for (int i = 0; i < tmps.length; i++) {
            if (nums[i] != tmps[i]) {
                start = i;
                break;
            }
        }
        for (int i = tmps.length - 1; i >= 0; i--) {
            if (nums[i] != tmps[i]) {
                end = i;
                break;
            }
        }
        return end - start + 1;
    }

    public int findUnsortedSubarray1(int[] A) {
        int n = A.length, beg = -1, end = -2, min = A[n-1], max = A[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[n-1-i]);
            if (A[i] < max) end = i;
            if (A[n-1-i] > min) beg = n-1-i;
        }
        return end - beg + 1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 6, 6, 4, 8, 10, 9, 15};
//        int[] nums = {2, 1};
//        int[] nums = {1, 3, 2, 2, 2};
//        int[] nums = {1, 2, 3, 3, 3};
        System.out.println(new FindUnsortedSubarray().findUnsortedSubarray(nums));
    }
}
