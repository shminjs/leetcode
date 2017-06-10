package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/10.
 * 326
 * Given integer,write a function to determine if it is a power of three.
 */
public class IsPowerOfThree {
    public boolean isPowerOfThree(int n) {
        return n > 0 && (1162261467 % n == 0);
    }

    public boolean isPowerOfThree1(int n) {
        // cheat answer
        if (n == 1 || n == 3 || n ==9 || n == 27 || n == 81 || n == 243 || n == 729
                || n == 2187 || n == 6561 || n == 19683 || n == 59049 || n == 177147 || n == 531441
                || n == 1594323 || n == 4782969 || n == 14348907 || n == 43046721 || n == 129140163
                || n == 387420489 || n == 1162261467)
            return true;
        return false;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println((int)Math.pow(3, i));
        }
        System.out.println("----------------");
        for (int i = 0; i < 100; i++) {
            System.out.println(3 * i);
        }
    }
}
