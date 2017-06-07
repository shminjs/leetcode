package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 371
 * Calculate the sum of two integers a and b, but you are not allowed to user operator + and -.
 */
public class GetSum {
    public int getSum(int a, int b) {
        int jw = a & b;
        int jg = a ^ b;
        while (jw != 0) {
            int t_a = jg;
            int t_b = jw << 1;
            jw = t_a & t_b;
            jg = t_a ^ t_b;
        }
        return  jg;
    }
}
