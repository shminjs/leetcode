package com.shminjs.leetcode.hard;

import java.util.Arrays;

/**
 * Created by shimin on 2017/11/7.
 */
public class MaxSumOfThreeSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] sums = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0;i < k; i++) {
            sum += nums[i];
        }
        sums[0] = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i-k] + nums[i];
            sums[i-k+1] = sum;
        }
        System.out.println(Arrays.toString(sums));
        int[][] dp = new int[sums.length][4];
        for (int i = 0; i < sums.length; i++) {
            dp[i][1] = Integer.MIN_VALUE;
            dp[i][2] = Integer.MIN_VALUE;
            dp[i][3] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < sums.length; i++) {
            dp[i][1] = Math.max(sums[i], i > 0 ? dp[i-1][1] : Integer.MIN_VALUE);
            dp[i][2] = Math.max(i>=k ? dp[i-k][1] + sums[i] : Integer.MIN_VALUE, i > 0 ? dp[i-1][2] : Integer.MIN_VALUE);
            dp[i][3] = Math.max(i>=k ? dp[i-k][2] + sums[i] : Integer.MIN_VALUE, i > 0 ? dp[i-1][3] : Integer.MIN_VALUE);
        }
        int[] res = new int[3];
        int index = 2;
        int first = -1;
        for (int i = dp.length - 1; i >= 0; i--) {
            if (index == 2) {
                // 判断第三个数
                if (dp[i][3] == dp[i-k][2] + sums[i]) {
                    res[index] = i;
                    index--;
                    i = i - k + 1;
                }
            } else if (index == 1) {
                if (dp[i][2] == dp[i-k][1] + sums[i]) {
                    res[index] = i;
                    first = dp[i-k][1];
                    index--;
                    i = i - k + 1;
                }
            } else if (index == 0) {
                if (dp[i][1] == first) {
                    res[index] = i;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MaxSumOfThreeSubarrays().maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2)));
    }
}
