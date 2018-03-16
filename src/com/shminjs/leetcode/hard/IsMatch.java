package com.shminjs.leetcode.hard;

/**
 * Created by shimin on 2017/11/29.
 */
public class IsMatch {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()] = true;
        // 初始化
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][p.length()] = false;
        }
        boolean flag = true;
        for (int j = p.length() - 1; j >= 0; j--) {
            if (flag && p.charAt(j) == '*') {
                dp[s.length()][j] = true;
            } else {
                flag = false;
                dp[s.length()][j] = false;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i][j+1] || dp[i+1][j] || dp[i+1][j+1];
                } else {
                    boolean first_match = p.charAt(j) == s.charAt(i) || p.charAt(j) == '?';
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
