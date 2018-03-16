package com.shminjs.leetcode.test;

import java.util.Arrays;

/**
 * Created by shimin on 2017/10/27.
 */
public class HelloPairs {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0;
        int p1 = 0, p2 = 1;
        while (p2 < nums.length) {
            if (nums[p2] - nums[p1] < k) {
                p2++;
            } else if (nums[p2] - nums[p1] == k) {
                res++;
                // handle p1, p2
                p1++;p2++;
                while (p1 < nums.length && nums[p1] == nums[p1-1]) {
                    p1++;
                }
                if (p2 <= p1) p2 = p1 + 1;
            } else {
                if (p1 + 1 < p2) p1++;
                else p2++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new HelloPairs().findPairs(new int[]{1, 1, 1, 1, 1}, 0));
    }
}
