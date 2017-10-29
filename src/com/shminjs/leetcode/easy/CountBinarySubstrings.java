package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/10/24.
 */
public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        int res = 0;
        int i = 0;
        for (; i < s.length();) {
            int count = 1;
            boolean flag = false;
            int flip = i + 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    if (!flag) count++;
                    else break;
                }
                else {
                    if (!flag) flip = j;
                    count--;
                    res++;
                    flag = true;
                    if (count == 0) break;
                }
            }
            i = flip;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountBinarySubstrings().countBinarySubstrings("1010001"));
    }
}
