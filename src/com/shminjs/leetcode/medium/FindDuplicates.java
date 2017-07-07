package com.shminjs.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/7/7.
 * 422
 * Given an array of integers, 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in that array.
 * Could you do it without extra space and in O(n) runtime?
 */
public class FindDuplicates {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i])-1] < 0) {
                res.add(Math.abs(nums[i]));
            } else {
                nums[Math.abs(nums[i])-1] = -nums[Math.abs(nums[i])-1];
            }
        }
        return res;
    }
}
