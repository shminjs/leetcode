package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/15.
 * We define the perfect number integer that is equal to the sum of all its positive divisors except itself.
 * Now, given an integer n, write a function that return true when it is a perfect number and false when it is not.
 */
public class CheckPerfectNumber {
    public boolean checkPerfectNumber(int num) {
        if (num <= 1) return false;
        int tmp = num;
        tmp = tmp - 1;
        int N = (int)Math.sqrt(num);
        for (int i = 2; i <= N; i++) {
            if (num % i == 0) {
                int k = num / i;
                tmp = tmp - (i + k);
            }
        }
        return tmp == 0;
    }

    public static void main(String[] args) {
        System.out.println(new CheckPerfectNumber().checkPerfectNumber(28));
    }
}
