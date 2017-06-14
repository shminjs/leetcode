package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/14.
 * 9
 * Determine whether an integer is a palindrome. To this without extra space.
 */
public class IsPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int tmp = x, r = 0;
        while (tmp > 0) {
            r = r * 10 + tmp % 10;
            tmp = tmp / 10;
        }
        return r == x;
    }
}
