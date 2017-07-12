package com.shminjs.leetcode.medium;

import javax.sound.midi.MidiChannel;

/**
 * Created by shimin on 2017/7/11.
 * 74
 * Write an efficient algorithm that search for a value in an m x n matrix. This matrix has following properties.
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean flag = false;
        for (int i = 0; i < matrix.length; i++) {
            if (target >= matrix[i][0]) {
                int ind = search(matrix[i], target);
                if (ind != -1) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    private int search(int[] matrix, int target) {
        int lo = 0, hi = matrix.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[mid] > target) {
                hi = mid - 1;
            } else if (matrix[mid] < target) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        System.out.println(searchMatrix.searchMatrix(new int[][]{{1}}, 1));
    }
}
