package com.shminjs.leetcode.hard;

import java.util.Arrays;

/**
 * Created by shimin on 2017/10/29.
 */
public class SmallestDistancePair {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        int low = nums[1] - nums[0];
        for (int i = 1; i <= n - 2; i++) {
            low = Math.min(low, nums[i+1] - nums[i]);
        }

        int high = nums[n-1] - nums[0];

        while (low < high) {
            int mid = (low + high) >> 1;
            if (countPairs(nums, n, mid) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int countPairs(int[] nums, int n, int mid) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += upper_bound(nums, i, n, nums[i]+mid) - (i + 1);
        }
        return res;
    }

    private int upper_bound(int[] nums, int start, int size, int key) {
        int first = start, len = size - start;
        int half, middle;

        while (len > 0) {
            half = len >> 1;
            middle = first + half;
            if (nums[middle] > key) {
                len = half;
            } else {
                first = middle + 1;
                len = len - half - 1;
            }
        }
        return first;
    }
}
