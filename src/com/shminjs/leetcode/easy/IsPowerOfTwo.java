package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/10.
 * 231
 * Given an integer, write a function to determine if it is a power of two.
 *
 * trick: Power of 2 means only one bit of n is '1', so use the trick n&(n-1)==0 to judge whether that is the case
 */
public class IsPowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (1073741824 % n == 0);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 40; i++) {
            System.out.println((int)Math.pow(2, i));
        }
    }
}
