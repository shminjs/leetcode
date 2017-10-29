package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/9/6.
 * 377
 */
public class CombinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
//        for (int i = 1; i <= target; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (nums[j] <= i) {
//                    dp[i] += dp[i-nums[j]];
//                }
//            }
//        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j <= target; j++) {
                dp[j] += dp[j-nums[i]];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum4().combinationSum4(new int[]{1, 2, 3}, 4));
    }
}
