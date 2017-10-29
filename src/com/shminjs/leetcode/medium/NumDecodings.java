package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/8/24.
 * 91
 */
public class NumDecodings {
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[][] dp = new int[s.length() + 1][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        char pre = '0';
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i-1) == '0') {
                if (pre == '1' || pre == '2')
                    dp[i][0] = dp[i-1][1];
                else return 0;
            } else if (pre == '1' || pre == '0' || (pre == '2' && s.charAt(i-1) < '7')) {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = dp[i-1][0] + dp[i-1][1];
            } else {
                // 此时不适合与前者连结
                dp[i][1] = dp[i-1][0] + dp[i-1][1];
            }
            pre = s.charAt(i-1);
        }
        return dp[s.length()][0] + dp[s.length()][1];
    }

    public static void main(String[] args) {
        System.out.println(new NumDecodings().numDecodings("10"));
    }
}
