package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/8/22.
 * 583
 */
public class MinDistance {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        int m = 0;
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                int tmp = 0;
                if (i - 1 < 0 || j - 1 < 0) {
                    tmp = 0;
                } else {
                    tmp = dp[i - 1][j - 1];
                }
                if (word1.charAt(i) == word2.charAt(j)) dp[i][j] = tmp + 1;
                else dp[i][j] = 0;
                m = Math.max(dp[i][j], 0);
            }
        }
        return word1.length() + word2.length() - 2 * m;
    }

    public static void main(String[] args) {
        System.out.println(new MinDistance().minDistance("sea", "eat"));
    }
}
