package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/8/21.
 * 661
 */
public class ImageSmoother {
    public int[][] imageSmoother(int[][] M) {
        int[][] result = new int[M.length][M[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                int row_start = i - 1 >= 0 ? i - 1 : 0, row_end = i + 1 == result.length ? i : i + 1;
                int column_start = j - 1 >= 0 ? j - 1 : 0, column_end = j + 1 == result[0].length ? j : j + 1;
                int temp = 0;
                int count = 0;
                for (int m = row_start; m <= row_end; m++) {
                    for (int n = column_start; n <= column_end; n++) {
                        temp += M[m][n];
                        count += 1;
                    }
                }
                result[i][j] = temp / count;
            }
        }
        return result;
    }
}
