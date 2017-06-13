package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/12.
 * 367
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 */
public class IsPerfectSquare {
    public boolean isPerfectSquare(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }

    public static void main(String[] args) {
        System.out.println(new IsPerfectSquare().isPerfectSquare(16));
    }
}
