package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/12.
 * 34
 * Given array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8.
 * return [3, 4]
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        int ind = binarysearch(nums, target);
        if (ind != -1) {
            res[0] = res[1] = ind;
            for (int i = ind + 1; i < nums.length; i++) {
                if (nums[i] == target) res[1] = i;
                else break;
            }
            return res;
        } else {
            return res;
        }
    }

    private int binarysearch(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int ind = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) {
                hi = mid - 1;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                ind = mid;
                hi = mid - 1;
            }
        }
        return ind;
    }
}
