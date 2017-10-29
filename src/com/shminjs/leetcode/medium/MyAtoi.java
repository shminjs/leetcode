package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/8/24.
 * 8
 */
public class MyAtoi {
    public int myAtoi(String str) {
        String num = str.trim();
        if (num.equals("") || (num.length() == 1 && !Character.isDigit(num.charAt(0)))) return 0;
        boolean pn = true; // 正数
        boolean overflow = false; // 是否溢出
        int res = 0;    // 结果
        int pre = 0;    // 保存之前的结果
        int start = 0;
        if (num.charAt(0) == '-')  {
            pn = false; start = 1;
        }
        if (num.charAt(0) == '+') start = 1;
        for (int i = start; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) break;
            int d = num.charAt(i) - '0';
            res = res * 10 + d;
            if (res < 0 || (res - d) / 10 != pre) {
                overflow = true;
                break;
            }
            pre = res;
        }
        if (overflow) {
            if (pn) return Integer.MAX_VALUE;
            else return Integer.MIN_VALUE;
        } else {
            if (pn) return res;
            else return -res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MyAtoi().myAtoi("2147483648"));
    }
}
