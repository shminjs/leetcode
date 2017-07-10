package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimin on 2017/7/10.
 * 54
 * Given a matrix of m x n elements (m rows, n columns), return all elements of matrix in spiral order.
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] flags = new boolean[m][n];
        int flag = m * n;
        int ind = 1;
        int i = 0, j = 0;
        int di = 0, dj = 1;
        while (ind <= flag) {
            result.add(matrix[i][j]);
            flags[i][j] = true;
            if (i + di == m || j + dj == n || j + dj == -1 || flags[i + di][j + dj]) {
                int tmp = di;
                di = dj;
                dj = -tmp;
            }
            ind++;
            i += di;
            j += dj;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 2}, {4, 5}, {7, 8}};
        SpiralOrder so = new SpiralOrder();
        List<Integer> res = so.spiralOrder(array);
        System.out.println(res.toString());
    }
}
