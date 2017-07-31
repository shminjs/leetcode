package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/31.
 * 31
 */
public class nextPermutation {
    public void nextPermutation(int[] num) {
        int n = num.length;
        if (n < 2)
            return;
        int index = n - 1;
        while (index > 0) {
            if (num[index-1] < num[index])
                break;
            index--;
        }
        if (index == 0) {
            reverseSort(num, 0, n-1);
        } else {
            int val = num[index-1];
            int j = n - 1;
            while (j >= index) {
                if (num[j] > val) {
                    break;
                }
                j--;
            }
            swap(num, j, index-1);
            reverseSort(num, index, n - 1);
        }
    }

    private void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

    private void reverseSort(int[] num, int start, int end) {
        while (start < end) {
            int tmp = num[start];
            num[start] = num[end];
            num[end] = tmp;
            start++;end--;
        }
    }
}
