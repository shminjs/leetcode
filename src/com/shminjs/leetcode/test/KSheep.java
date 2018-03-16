package com.shminjs.leetcode.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by shimin on 2017/9/10.
 */
public class KSheep {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] sn = new int[N];
        for (int i = 0; i < N; i++) {
            sn[i] = sc.nextInt();
        }
        Arrays.sort(sn);
        int res = 1;
        int k = 0;
        for (int i = 0; i < sn.length; i++) {
            res = res * (sn[i]-k) % 1000000007;
            k++;
        }
        System.out.println(res);
    }
}
