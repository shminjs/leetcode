package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/5.
 * In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original
 * data.
 * You're give amatrix represented by a two-dimensional array, and tow psitive integers r and c representing the row number and column number of
 * the wanted reshaped matrix, respectively.
 * The reshaped matrix need to be filled with all element of the original matrix in the sam row-traversing order as the were.
 * If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
 */
public class MatrixReshape {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int r_o = nums.length, c_o = nums[0].length;
        if (r_o * c_o == r * c) {
            int[][] nums_re = new int[r][c];
            int index = 0;
            int r_t, c_t;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    r_t = index / c_o; c_t = index % c_o;
                    nums_re[i][j] = nums[r_t][c_t];
                    index++;
                }
            }
            return nums_re;
        } else {
            return nums;
        }
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 2}, {3, 4}};
        MatrixReshape mr = new MatrixReshape();
        int[][] nums_re = mr.matrixReshape(nums, 1, 4);
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(nums_re[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
