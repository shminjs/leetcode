package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/8.
 * Given two strings s and t, wreite a function to determine if t is an anagram of s.
 * For example.
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car" return false
 */
public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        int[] s_a = new int[26];
        int[] t_a = new int[26];
        for (int i = 0; i < s.length(); i++)
            s_a[s.charAt(i)-97]++;
        for(int i = 0; i < t.length(); i++)
            t_a[t.charAt(i)-97]++;
        for(int i = 0; i < 26; i++) {
            if (s_a[i] != t_a[i])
                return false;
        }
        return true;
    }
}
