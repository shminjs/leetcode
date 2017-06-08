package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/8.
 * 217
 * Given an arrayof integers, find if the aray contains any duplicates. You function should return true if any value appears at list
 * twice in the array, and it should return false if every element is distinct.
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) return true;
        }
        return false;
    }
}
