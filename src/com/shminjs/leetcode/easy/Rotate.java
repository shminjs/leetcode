package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/24.
 * 189
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1, 2, 3, 4, 5, 6, 7] is rotated to [5, 6, 7, 1, 2, 3, 4]
 */
public class Rotate {
    public void rotate(int[] nums, int k) {
        int N = nums.length;
        k = N - k % N;
        if (k == 0) return;
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int N = start + (end + 1 - start) / 2;
        for (int i = start; i < N; i++) {
            int swap = nums[i];
            nums[i] = nums[end-i+start];
            nums[end-i+start] = swap;
        }
    }

    public static void main(String[] args) {
        Rotate r = new Rotate();
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        r.rotate(array, 3);
//        r.reverse(array, 0, 6);
        System.out.println(Arrays.toString(array));
    }
}
