package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/16.
 * 67
 * Given two binary strings, return their sum (also a binary string).
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        int N = a.length() > b.length() ? a.length()+1:b.length()+1;
        char[] arr = new char[N];
        Arrays.fill(arr, '0');
        int k1 = a.length() - 1, k2 = b.length() - 1;
        int carry = 0;
        int ind = N - 1;
        for (; k1 >= 0 && k2 >= 0; k1--, k2--) {
            int tmp1 = a.charAt(k1) - '0';
            int tmp2 = b.charAt(k2) - '0';
            int v = (tmp1 ^ tmp2 ^ carry) == 0 ? 0:1;
            arr[ind--] = (char)(v + '0');
            carry = (tmp1 + tmp2 + carry) >= 2 ? 1:0;
        }
        for (;k1>=0;k1--) {
            int tmp1 = a.charAt(k1) - '0';
            int v = (tmp1 ^ carry) == 0 ? 0:1;
            arr[ind--] = (char)(v + '0');
            carry = (tmp1 + carry) >= 2 ? 1:0;
        }
        for (;k2>=0;k2--) {
            int tmp2 = b.charAt(k2) - '0';
            int v = (tmp2 ^ carry) == 0 ? 0:1;
            arr[ind--] = (char)(v + '0');
            carry = (tmp2 + carry) >= 2 ? 1:0;
        }
        arr[ind] = (char)(carry + '0');
        if (arr[ind] == '0') {
            return new String(arr, 1, N-1);
        } else {
            return new String(arr);
        }
    }

    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("11", "1"));
    }
}
