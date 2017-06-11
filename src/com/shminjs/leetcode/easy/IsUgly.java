package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/11.
 * 263
 * Write a program to check whether a given number is an ugly number.
 * Ugly number are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes
 * another prime factor 7.
 * Note that 1 is typically treated as an ugly number.
 */
public class IsUgly {
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;
        return (num % 2 == 0 && isUgly(num/2)) || (num % 3 == 0 && isUgly(num/3)) || (num % 5 == 0 && isUgly(num/5)) || false;
    }

    public static void main(String[] args) {
        IsUgly iu = new IsUgly();
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + iu.isUgly(i));
        }
    }
}
