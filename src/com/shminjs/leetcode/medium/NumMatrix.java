package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/9/30.
 * 304
 */
public class NumMatrix {
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int[][] aux1 = new int[matrix.length][matrix[0].length];
        int[][] aux2 = new int[matrix.length][matrix[0].length];
        dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int front = j > 0 ? aux1[i][j-1] : 0;
                aux1[i][j] = front + matrix[i][j];
                int top = i > 0 ? aux2[i-1][j] : 0;
                aux2[i][j] = top + matrix[i][j];
                int left = j > 0 ? aux1[i][j-1] : 0;
                int up = i > 0 ? aux2[i-1][j] : 0;
                int cor = (i > 0 && j > 0) ? dp[i-1][j-1] : 0;
                dp[i][j] = left + up + cor + matrix[i][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (dp == null) return 0;

        return dp[row2][col2] - dp[row1][col2] - dp[row2][col1] + dp[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
    }
}
