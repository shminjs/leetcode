package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/5.
 * 496
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1's elements are subset of nums2. Find all next greater
 * numbers for nums1's elements in the corresponding places of nums2.
 *
 * The next Greater Number of a numberx in nums1 is the first greater number to its right in nums2. If it doesn't exist, output -1 for
 * this number.
 */
public class NextGreaterElement {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] results = new int[findNums.length];
        for (int i = 0; i < findNums.length; i++) {
            results[i] = detect(nums, findNums[i], i);
        }
        return results;
    }

    public static int detect(int[] nums, int v, int ind) {
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (flag) {
                if (nums[i] > v) return nums[i];
                continue;
            }
            if (nums[i] == v) {
                flag = true;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] findNums = {4, 1, 2};
        int[] nums = {1, 3, 4, 2};
        NextGreaterElement nge = new NextGreaterElement();
        int[] results = nge.nextGreaterElement(findNums, nums);
        System.out.println(Arrays.toString(results));
    }
}
