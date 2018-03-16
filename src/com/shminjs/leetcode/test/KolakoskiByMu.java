package com.shminjs.leetcode.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by shimin on 2017/8/30.
 */
public class KolakoskiByMu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int  n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        if (n == 0 || m == 0) {
            System.out.println(0);
        } else {
            int[] a   = new int[m];
            int[] res = new int[n];
            for (int i = 0; i < m; i++) {
                a[i] = sc.nextInt();
            }
            int sum = 0;
            for (int i = 0; i < a[0]&&sum<n; i++) {
                res[i] = a[0];
                sum++;
            }
            int mod = 1;
            for (int i = 1; i < n && sum < n; i++) {
                if (res[i] == 0) {
                    res[i] = a[mod];
                }
                for (int j = 0; j < res[i]&&sum<n; j++) {
                    res[sum++] = a[mod];
                }
                mod++;
                mod %= m;
            }
            for (int i = 0; i < n; i++) {
                System.out.print(res[i] + "\t");
            }
            int[] res2 = new Kolakoski().kolakoski(a, n);
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print(res2[i] + "\t");
            }
            System.out.println(Arrays.equals(res, res2));
            System.out.println(Arrays.equals(new int[]{1, 3, 4}, new int[]{1, 3, 4}));
        }
    }
}
