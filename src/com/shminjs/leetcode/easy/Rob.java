package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/11.
 * 198
 * You are a prefessional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only
 * constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically
 * contact the police if two adjacent houses were broken into on the same night.
 * Given a lis of non-negative representing the amount of money of each house, determine the maximum amount of money you can rob
 * tonight without alerting the police.
 */
public class Rob {
    public int rot(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < 2 && i < nums.length; i++) dp[i] = nums[i];
        if (1 < nums.length) dp[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
        Rob ro = new Rob();
        System.out.println(ro.rot(nums));
    }
}
