package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/7.
 * 238
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal the product of all the element of
 * nums except nums[i].
 * Solve without divisioon and in O(n)
 * For example, given [1, 2, 3, 4], return 24, 12, 8, 6.
 */
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int sum = 1;
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) zero++;
            else sum *= nums[i];
        }
        int[] res = new int[nums.length];
        if (zero >= 2) return res;
        else if (zero == 1) {
            for (int i = 0; i < res.length; i++) {
                if (nums[i] == 0) res[i] = sum;
            }
        } else {
            for (int i = 0; i < res.length; i++) {
                res[i] = sum / nums[i];
            }
        }
        return res;
    }
}
