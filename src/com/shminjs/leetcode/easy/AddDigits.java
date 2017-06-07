package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 258
 * Given a non-negative integer num, repeatedly add all its digits until the result has only digit.
 * For example:
 * Given num = 38, the process like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 */
public class AddDigits {
    public int addDigits(int num) {
        while (num > 10) {
            num = digitsSum(num);
        }
        return num;
    }

    private static int digitsSum(int num) {
        int[] digits = new int[10];
        int[] pows = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
        for (int i = 9; i >= 0; i--) {
            digits[i] = (num / pows[i]);
            num -= digits[i] * pows[i];
        }
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += digits[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        AddDigits ad = new AddDigits();
        System.out.println(ad.addDigits(38));
    }
}

//int addDigits(int num) {
//    return 1 + (num - 1) % 9;
//}
