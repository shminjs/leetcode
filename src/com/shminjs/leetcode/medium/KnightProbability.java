package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/10/1.
 * 688
 */
public class KnightProbability {
    private static int[][] dirs = new int[][]{{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};

    public double knightProbability(int N, int K, int r, int c) {
        double[][] dp = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = 1.0;
            }
        }

        double[][] temp = new double[N][N];

        for (int k = 0; k < K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    double prob = 0.0;
                    for (int[] dir : dirs) {
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                            prob += dp[nx][ny] / 8.0;
                        }
                    }
                    temp[i][j] = prob;
                }
            }
            double[][] swap = dp;
            dp = temp;
            temp = swap;
        }
        return dp[r][c];
    }
}
