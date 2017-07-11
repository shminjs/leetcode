package com.shminjs.leetcode.medium;

import com.sun.rowset.JdbcRowSetImpl;
import com.sun.rowset.internal.Row;

/**
 * Created by shimin on 2017/7/11.
 * 73
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0, do it in place.
 */
public class SetZeros {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int col0 = 1;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0 == 0) matrix[i][0] = 0;
        }
    }

    private void setZeroes(int[][] matrix, int row, int column) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][column] != 0) {
                matrix[i][column] = 0;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[row][j] != 0) {
                matrix[row][j] = 0;
            }
        }
    }
}
