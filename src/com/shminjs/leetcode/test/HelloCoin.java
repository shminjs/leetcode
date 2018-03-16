package com.shminjs.leetcode.test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by shimin on 2017/9/27.
 */
public class HelloCoin {
    public static int findMaxMolecules(int massA, int massB, int massC, int massD, int massX) {
        // dp
        int[] wt = new int[]{massA, massB, massC * 2, massD * 2};
        int[] val = new int[] {1, 1, 2, 2};

        return knapSack(massX, wt, val, 4);
    }

    private static int knapSack(int W, int[] wt, int val[], int n) {
        int[] dp = new int[W+1];
        Arrays.fill(dp, 0);

        int ans = 0;
        for (int i = 0; i <= W; i++) {
            for (int j = 0; j < n; j++) {
                if (wt[j] <= i) {
                    dp[i] = Math.max(dp[i], dp[i-wt[j]] + val[j]);
                }
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        System.out.println(HelloCoin.findMaxMolecules(2, 4, 1, 1,  11));
    }
}
