package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/13.
 * 55
 */
public class CanJump {
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        int last = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= last) {
                int ind = nums[i] + i < nums.length ? nums[i] + i : nums.length-1;
                dp[ind] = true;
                last = Math.max(ind, last);
            }
        }
        return dp[nums.length-1];
    }
}
