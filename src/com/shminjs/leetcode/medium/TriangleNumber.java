package com.shminjs.leetcode.medium;

import java.util.Arrays;

/**
 * Created by shimin on 2017/7/10.
 * 611
 * Given an array consists of non-negative integers, your tasks is to coun the number of triplets chosen from the array that can make
 * triangles if we take them as side length of a triangle.
 */
public class TriangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r-l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = {2, 2, 3, 4};
        TriangleNumber tn = new TriangleNumber();
        System.out.println(tn.triangleNumber(array));
    }
}
