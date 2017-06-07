package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 598
 * Given an m * n matrix M initialized with all 0's and several update operations.
 * Operations are represented by a 2D array, and each operation is represented by an array with tow positive integers a and b,
 * which means M[i][j] should be added by one for all 0<=i<a and 0<=j<b.
 * You need to count and return the number of maximum integers int the matrix after performing all the operations.
 */
public class MaxCount {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) return m*n;
        int x_min = Integer.MAX_VALUE, y_min = Integer.MAX_VALUE;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i][0] < x_min) {
                x_min = ops[i][0];
            }
            if (ops[i][1] < y_min) {
                y_min = ops[i][1];
            }
        }
        return x_min * y_min;
    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] ops = {{2, 2}, {3, 3}};
        MaxCount mc = new MaxCount();
        System.out.println(mc.maxCount(m, n, ops));
    }
}
