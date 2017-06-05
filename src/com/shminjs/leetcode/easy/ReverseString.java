package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/5.
 * Write a function that takes a string as input and returns the string reversed.
 */
public class ReverseString {
    public String reverseString(String s) {
        char[] reverse = new char[s.length()];
        int N = reverse.length;
        for (int i = 0; i < N; i++) {
           reverse[i] = s.charAt(N-i-1);
        }
        return new String(reverse);
    }
}
