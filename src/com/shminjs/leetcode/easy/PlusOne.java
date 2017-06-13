package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/12.
 * 66
 * Plus One
 * Given a non-negative integer represented as an non-empty array of digits, plus one the the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        boolean flag = true;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                flag = false;
                break;
            }
        }
        int[] results = null;
        if (flag) results = new int[digits.length + 1];
        else results = new int[digits.length];
        results[digits.length - 1] = digits[digits.length - 1] + 1;
        int carray = 0;
        if (results[digits.length-1] >= 10) {
            carray = 1;
            results[digits.length-1] -= 10;
        }
        for (int i = digits.length - 2; i >= 0; i--) {
            results[i] = digits[i] + carray;
            if (results[i] >= 10) {
                carray = 1;
                results[i] -= 10;
            } else {
                carray = 0;
            }
        }
        if (carray == 1) {
            results[0] = 1;
        }
        return results;
    }

    public static void main(String[] args) {
        int[] digits = {1};
        PlusOne po = new PlusOne();
        System.out.println(Arrays.toString(po.plusOne(digits)));
    }
}
