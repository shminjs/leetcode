package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/4.
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary represents.
 */
public class FindComplement {
    public int findComplement(int num) {
        int mask = ~0;
        while ((num & mask) != 0) mask <<= 1;
        return num ^ ~mask;
    }
}
