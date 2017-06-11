package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/11.
 * 191
 * Write a function that takes an unsigned integer and return the number of '1' bit it has (also known as the Hamming weight).
 * For example, the 32-bit integer '11' ha binary representation 1011, so the function return 3.
 */
public class HammingWeight {
    public int hammingWeight(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (((n>>>i)&0x1) == 1) result++;
        }
        return result;
    }
}
