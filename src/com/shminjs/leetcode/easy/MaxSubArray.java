package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/10.
 * 53
 * Find the contiguous subarray within an array (containing at least number) which has largest sum.
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int maxsofar = nums[0];
        int maxendinghere = nums[0];
        for (int i = 1; i < nums.length; i++) {
//            if (maxendinghere + nums[i] > maxendinghere) {
//                maxendinghere = maxendinghere + nums[i];
//            } else {
//                maxendinghere = nums[i];
//            }
            maxendinghere = Math.max(maxendinghere + nums[i], nums[i]);
            maxsofar = Math.max(maxsofar, maxendinghere);
        }
        return maxsofar;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = {-11, -5, -3, -1, -4, -9};
//        int[] nums = {-1};
        MaxSubArray msa = new MaxSubArray();
        System.out.println(msa.maxSubArray(nums));
    }
}
