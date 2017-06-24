package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/24.
 * 532
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is difined
 * as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.
 */
public class FindPairs {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        if (nums.length <= 1) return 0;
        int result = 0;
        int p1 = 0, p2 = 1;
        int pre = Integer.MIN_VALUE;
        while (p2 < nums.length) {
            int tmp = nums[p2] - nums[p1];
            if (tmp == k && nums[p1] != pre) {
                result++;
                pre = nums[p1];
                p1++;
                p2++;
            } else if (tmp < k) {
                p2++;
            } else {
                p1++;
                p2 = p1 + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new FindPairs().findPairs(new int[]{3, 1, 4, 1, 5}, 2));
        System.out.println(new FindPairs().findPairs(new int[]{1, 2, 3, 4, 5}, 1));
        System.out.println(new FindPairs().findPairs(new int[]{1, 3, 1, 4, 5}, 0));
        System.out.println(new FindPairs().findPairs(new int[]{1, 1, 1, 1, 1}, 0));
    }
}
