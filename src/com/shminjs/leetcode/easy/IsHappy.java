package com.shminjs.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by shimin on 2017/6/10.
 * 202
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of
 * its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those
 * numbers for which this process ends in 1 are happy numbers.
 */
public class IsHappy {
    public boolean isHappy(int n) {
        int num = digitsSum(n);
        HashSet<Integer> set = new HashSet<>();
        while (true) {
            int n3 = num / 100;
            num = num - n3 * 100;
            int n2 = num / 10;
            num = num - n2 * 10;
            int n1 = num;
            num = n1 * n1 + n2 * n2 + n3 * n3;
            if (num == 1 || num == 10 || num == 100) {
                return true;
            }
            if (!set.contains(num)) {
                set.add(num);
            } else {
                return false;
            }
        }
    }

    private int digitsSum(int num) {
        int[] digits = new int[10];
        int[] pows = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
        for (int i = 9; i >= 0; i--) {
            digits[i] = (num / pows[i]);
            num -= digits[i] * pows[i];
        }
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += digits[i] * digits[i];
        }
        return result;
    }
}