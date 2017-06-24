package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/24.
 * 28
 * Implement strStr().
 * Returns the index of the first occurrence of neddle in haystack, or -1 if needle is not part of haystack.
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        if (haystack.length() == 0 && needle.length() == 0) return 0;
        int hs = 0, ns = 0;
        while (hs < haystack.length() && ns < needle.length()) {
            if (haystack.charAt(hs) == needle.charAt(ns)) {
                hs++;
                ns++;
                if (ns == needle.length()) {
                    return hs-ns;
                }
            } else {
                hs = hs - ns + 1;
                ns = 0;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new StrStr().strStr("hello", "ll"));
    }
}
