package com.shminjs.leetcode.medium;

import com.shminjs.leetcode.easy.Intersect2;

/**
 * Created by shimin on 2017/7/10.
 * 64
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its paths.
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int left = j - 1 > -1 ? dp[i][j-1] : Integer.MAX_VALUE;
                int up = i - 1 > -1 ? dp[i-1][j] : Integer.MAX_VALUE;
                if (left != Integer.MAX_VALUE || up != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(left, up) + grid[i][j];
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
