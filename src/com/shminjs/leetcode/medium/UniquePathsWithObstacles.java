package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/10.
 * 63
 */
public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        if (obstacleGrid[0][0] == 1) return 0;
        else dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    int left = j - 1 > -1 ? dp[i][j-1] : 0;
                    int up = i - 1 > -1 ? dp[i-1][j] : 0;
                    dp[i][j] += left + up;
                }
            }
        }
        return dp[m-1][n-1];
    }
}
