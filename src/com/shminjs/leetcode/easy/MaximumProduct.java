package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/7/7.
 * 628
 */
public class MaximumProduct {
    public int maximumProduct(int[] nums) {
        int pm1 = Integer.MIN_VALUE;
        int pm2 = Integer.MIN_VALUE;
        int pm3 = Integer.MIN_VALUE;
        int nm1 = 1, nm2 = 1;
        for (int i = 0; i < nums.length; i++) {
            if (pm1 == Integer.MIN_VALUE || nums[i] > pm1) {
                pm3 = pm2;
                pm2 = pm1;
                pm1 = nums[i];
            } else if (pm2 == Integer.MIN_VALUE || nums[i] > pm2) {
                pm3 = pm2;
                pm2 = nums[i];
            } else if (pm3 == Integer.MIN_VALUE || nums[i] > pm3) {
                pm3 = nums[i];
            }
            if (nums[i] < 0 && nums[i] < nm1) {
                nm2 = nm1;
                nm1 = nums[i];
            } else if (nums[i] < 0 && nums[i] < nm2) {
                nm2 = nums[i];
            }
        }
        if (nm1 == 1 || nm2 == 1) {
            return pm1 * pm2 * pm3;
        } else {
            return Math.max(pm1 * pm2 * pm3, pm1 * nm2 * nm1);
        }
    }
}
