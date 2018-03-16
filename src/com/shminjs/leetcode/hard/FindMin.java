package com.shminjs.leetcode.hard;

/**
 * Created by shimin on 2017/11/8.
 */
public class FindMin {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length-1);
    }

    private int findMin(int[] nums, int lo, int hi) {
        if (lo > hi || hi < lo) {
            return Integer.MAX_VALUE;
        } else if (lo == hi) {
            return nums[lo];
        } else {
            int min = Integer.MAX_VALUE;
            int mid = lo + (hi - lo) / 2;
            min = Math.min(min, nums[mid]);
            if (nums[mid] < nums[hi]) {
                return Math.min(min, findMin(nums, lo, mid-1));
            } else if (nums[lo] < nums[mid]) {
                min = Math.min(min, nums[lo]);
                return Math.min(min, findMin(nums, mid + 1, hi));
            } else {
                return Math.min(min, Math.min(findMin(nums, lo, mid-1), findMin(nums, mid + 1, hi)));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new FindMin().findMin(new int[]{2, 1, 1, 2, 2, 2, 2}));
    }
}
