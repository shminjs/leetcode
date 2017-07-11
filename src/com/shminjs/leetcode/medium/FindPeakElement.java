package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/11.
 * 162
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        return peak(nums, 0, nums.length - 1);
    }

    private int peak(int[] nums, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int left = peak(nums, lo, mid);
            int right = peak(nums, mid+1, hi);
            return nums[left] > nums[right] ? left : right;
        } else {
            return lo;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2};
        FindPeakElement pe = new FindPeakElement();
        System.out.println(pe.findPeakElement(nums));
    }
}
