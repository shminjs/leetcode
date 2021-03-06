package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/4.
 * 96
 * Given n, how many structurally unique BST's (binary search trees) that store values 1..n?
 */
public class NumTrees {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++)
                dp[i] += dp[j-1] * dp[i-j];
        }
        return dp[n];
    }
}
