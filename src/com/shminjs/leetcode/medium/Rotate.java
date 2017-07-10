package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/10.
 * 48
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise)
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        for (int layer = 0; layer < N / 2; layer++) {
            int first = layer;
            int last = N - 1 -layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last-offset][first];
                matrix[last-offset][first] = matrix[last][last-offset];
                matrix[last][last-offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
    }
}
