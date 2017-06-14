package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/14.
 * 172
 * Given an integer n, return the number of trailing zeroes in n!.
 */
public class TrailingZeroes {
    private static int[] base = {5, 25, 125, 625, 3125, 15625, 78125, 390625, 1953125, 9765625, 48828125, 244140625, 1220703125};
    public int trailingZeroes(int n) {
        int result = 0;
        for (int i = 0; i < base.length; i++) {
            result += n / base[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new TrailingZeroes().trailingZeroes(50));
    }
}