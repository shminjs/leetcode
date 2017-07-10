package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shimin on 2017/7/10.
 * 59
 * Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] spiral = new int[n][n];
        if (n <= 0) return spiral;
        int i = 0, j = 0;
        int ind = 1;
        int di = 0, dj = 1;
        while (ind <= n * n) {
            spiral[i][j] = ind++;
            if (i + di == n || j + dj == n || j  + dj == -1 || spiral[i + di][j + dj] != 0) {
                int tmp = di;
                di = dj;
                dj = -tmp;
            }
            i += di;
            j += dj;
        }
        return spiral;
    }

    public static void main(String[] args) {
        GenerateMatrix gm = new GenerateMatrix();
        int[][] result = gm.generateMatrix(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
