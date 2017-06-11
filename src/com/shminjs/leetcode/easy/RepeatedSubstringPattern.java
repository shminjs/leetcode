package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/11.
 * 459
 * Given a non-empty string check if it can be constructed by taking a substring of it and append multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        int start = 0;
        for (int i = 0; i < s.length() / 2; i++) {
            if (detect(start, i, s)) {
                return true;
            }
        }
        return false;
    }

    private boolean detect(int start, int end, String s) {
        int skip = end - start + 1;
        if (s.length() % skip != 0) return false;
        for (int i = skip; i + skip <= s.length(); i += skip) {
            for (int j = i; j < i + skip; j++) {
                if (s.charAt(j) != s.charAt(j-skip)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        RepeatedSubstringPattern rsp = new RepeatedSubstringPattern();
        String s = "abcabcabcabc";
        String s1 = "aabaaba";
        String s2 = "abaababaab";
        String s4 = "abac";
        System.out.println(rsp.repeatedSubstringPattern(s4));
    }
}
