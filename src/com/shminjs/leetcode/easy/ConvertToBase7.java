package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/8.
 * 504
 * Given an integer, return its base 7 string representation.
 */
public class ConvertToBase7 {
    private static int[] base7 = {5764801, 823543, 117649, 16807, 2401, 343, 49, 7, 1};

    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) return "0";
        if (num < 0) {
            sb.append("-");
            num = -num;
        }
        boolean flag = false;
        for (int i = 0; i < base7.length; i++) {
            int temp = num / base7[i];
            num = num - base7[i] * temp;
            if (temp != 0) flag = true;
            if (flag) sb.append(Integer.toString(temp));
        }
        return sb.toString();
    }
}
