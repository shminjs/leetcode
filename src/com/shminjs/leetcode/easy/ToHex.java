package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/9.
 * 405
 * Given an integer write an algorithm to convert it to hexadecimal. For negative integer, two's complement method is used.
 */
public class ToHex {
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public String toHex(int num) {
        char[] result = new char[8];
        int bit = 0xf0000000;
        for (int i = 0; i < 8; i++) {
            int temp = bit & num;
            temp = temp >>> (32 - (i+1)*4);
            result[i] = digits[temp];
            bit = bit >>> 4;
        }
        int ind = 7;
        for (int i = 0; i < 8; i++) {
            if (result[i] != '0') {
                ind = i;
                break;
            }
        }
        return new String(result, ind, 8-ind);
    }

    public static void main(String[] args) {
    }
}
