package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/24.
 * 7
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 *
 * NOTE: 溢出的话，还原出的数和上一个数不想等。
 */
public class Reverse {
    public int reverse(int x) {
        boolean flag = false;
        if (x < 0) {
            flag = true;
            x = -x;
        }
        long k = (long)x;
        long result = 0;
        while (k > 0) {
            long dig = k % 10;
            k = k / 10;
            result = result * 10 + dig;
        }
        if (result > Integer.MAX_VALUE) return 0;
        else if (flag) return (int)-result;
        else return (int)result;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(123) + " " + Integer.toBinaryString(321));
//        System.out.println(Integer.toBinaryString(-123) + " " + Integer.toBinaryString(-321));
        System.out.println(new Reverse().reverse(1534236));
    }
}
