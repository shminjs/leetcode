package com.shminjs.leetcode.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by shimin on 2017/8/31.
 */
public class HelloTest {
    public boolean isPossible(int[] sn) {
        if (sn.length < 2) return false;
        Arrays.sort(sn);
        int left = sn[sn.length-1];
        for (int i = sn.length - 2; i >= 0; i--) {
            if (left < sn[i]) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] sn = new int[n];
        for (int i = 0; i < n; i++) {
            sn[i] = sc.nextInt();
        }
        System.out.println(new HelloTest().isPossible(sn));
    }
}
