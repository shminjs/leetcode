package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/18.
 * 400
 * Find the n-th digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7
 */
public class FindNthDigit {
    private static int[] digits = {0, 9, 90, 900, 9000, 90000, 900000, 9000000, 90000000, 900000000};
    private static int[] decimals = {0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    public int findNthDigit(int n) {
//        int tmp = n;
//        int i = 0;
//        for (; i < digits.length; i++) {
//            if (tmp > digits[i] * i) {
//                tmp -= digits[i] * i;
//            } else {
//                break;
//            }
//        }
//        int m = (int)Math.ceil(1.0 * (tmp-1) / i);
//        int k = (tmp-1) % (i);
//        int num = decimals[i] + m;
//        int s = decimals[i];
//        int result = 0;
//        for (; k >= 0; k--) {
//            result = num / s;
//            num = num - result * s;
//            s = s / 10;
//        }
//        return result;

        int len = 1;
        long count = 9;
        int start = 1;

        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }

    public static void main(String[] args) {
        System.out.println(new FindNthDigit().findNthDigit(1000000000));
    }
}
