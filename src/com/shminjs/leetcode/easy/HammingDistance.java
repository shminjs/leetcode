package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/4.
 * The Hamming distance between tow integers is the number of positions at which corresponding bits are different.
 * Given two integers x and y, calculate the hamming distance.
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int temp = x ^ y;
        int result = 0;
        while (temp != 0) {
            if ((temp & 0x1) == 1) result++;
            temp = temp >> 1;
        }
        return result;
    }
}
