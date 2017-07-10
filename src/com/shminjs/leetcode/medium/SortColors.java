package com.shminjs.leetcode.medium;

import java.util.Arrays;

/**
 * Created by shimin on 2017/7/10.
 * 75
 * Given an array with n objects colored red, white, or blue, sort them so that objects of the same color are adjacent, with the
 * color in the order red, white and blue.
 * Here we will use the integer 0, 1, and 2 to represent the color red, white, and blue respectively.
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int N = nums.length;
        int begin = 0, last = N - 1;
        int ind = 0;
        while (ind <= last) {
            if (nums[ind] == 0) swap(nums, ind++, begin++);
            else if (nums[ind] == 2) swap(nums, ind, last--);
            else ind++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 0};
        SortColors sc = new SortColors();
        sc.sortColors(array);
        System.out.println(Arrays.toString(array));
    }
}
