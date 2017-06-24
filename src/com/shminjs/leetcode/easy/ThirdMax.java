package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/24.
 * 414
 * Given non-empty array of integers, return the third maximum number in this array. if it does not exist, return the maximum number.
 * The time complexity must be in O(n).
 */
public class ThirdMax {
    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 = Long.MIN_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
                result++;
            } else if (nums[i] != max1 && nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
                result++;
            } else if (nums[i] != max1 && nums[i] != max2 && nums[i] > max3) {
                max3 = nums[i];
                result++;
            }
        }
        if (result > 2) {
            return (int)max3;
        } else {
            return (int)max1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ThirdMax().thirdMax(new int[]{2, 1, 1}));
        System.out.println(new ThirdMax().thirdMax(new int[]{2, 2, 3, 1}));
        System.out.println(new ThirdMax().thirdMax(new int[]{1, 2, 2, 5, 3, 5}));
        System.out.println(new ThirdMax().thirdMax(new int[]{1, 2, Integer.MIN_VALUE}));
    }
}
