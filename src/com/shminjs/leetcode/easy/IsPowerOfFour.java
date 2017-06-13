package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/11.
 * 342
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 */
public class IsPowerOfFour {
    public boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        return (num & 0xaaaaaaaa) == 0 && Integer.bitCount(num & 0x55555555) == 1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}
