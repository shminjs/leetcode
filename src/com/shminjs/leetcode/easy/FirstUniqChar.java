package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/8.
 * 387
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 */
public class FirstUniqChar {
    public int firstUniqChar(String s) {
        int[] sub = new int[26];
        for (int i = 0; i < s.length(); i++)
            sub[s.charAt(i)-97]++;

        for (int i = 0; i < s.length(); i++) {
            if (sub[s.charAt(i)-97] == 1)
                return i;
        }
        return -1;
    }
}
