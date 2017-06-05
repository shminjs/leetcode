package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/5.
 * 575
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means
 * one candy of corresponding kind. You need to distribute thiese candies equally in number to brother and sister. Return the maximum number
 * of kinds of candies the sister could gain.
 */
public class DistributeCandies {
    public int distributeCandies(int[] candies) {
        // Arrays.sort(candies);//效率反而高
        sort(candies, 0, candies.length - 1);
        int result = 1;
        for (int i = 1; i < candies.length; i++) {
            if (candies[i] != candies[i-1]) {
                result++;
            }
        }
        if (result <= candies.length/2) {
            return result;
        } else {
            return candies.length/2;
        }
    }

    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        int v = a[lo];
        while (i <= gt) {
            int cmp = Integer.compare(a[i], v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(int[] a, int v, int w) {
        int temp = a[v];
        a[v] = a[w];
        a[w] = temp;
    }
}
