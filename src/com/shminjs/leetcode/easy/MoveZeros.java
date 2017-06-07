package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/6.
 * 283
 * Given an array nums, write a function to move all 0's to the end of it while the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int zero = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (zero == nums.length && nums[i] == 0) {
                zero = i;
            }
            if (zero != nums.length && nums[i] != 0) {
                nums[zero] = nums[i];
                zero++;
            }
        }
        for (int i = zero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 0, 3, 12};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(array);
        System.out.println(Arrays.toString(array));
    }
}
