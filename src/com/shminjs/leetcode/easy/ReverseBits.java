package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/21.
 * 190
 * Reverse bits of a given 32 bits unsigned integer.
 */
public class ReverseBits {
    public int reverseBits(int n) {
        int tmp = 0;
        int mask = 0x00000001;
        for (int i = 0; i < 32; i++) {
            tmp <<= 1;
            if ((n & (mask << i)) != 0) {
                tmp = tmp | 0x1;
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseBits().reverseBits(43261596));
    }
}
