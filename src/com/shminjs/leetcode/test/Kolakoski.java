package com.shminjs.leetcode.test;

import java.util.Arrays;

/**
 * Created by shimin on 2017/8/30.
 */
public class Kolakoski {
    public int[] kolakoski(int[] a, int n) {
        int[] res = new int[n];
        int ind1 = 0, ind2 = 0;
        int N = a[ind1];
        int val = a[ind2];
        for (int i = 0; i < n; i++) {
            res[i] = val;
            N--;
            if (N == 0) {
                ind1++;
                ind2 = (ind2 + 1) % a.length;
                val = a[ind2];
                if (ind1 > i) {
                    N = val;
                } else {
                    N = res[ind1];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Kolakoski().kolakoski(new int[]{2, 1, 3, 1}, 10)));
    }
}
