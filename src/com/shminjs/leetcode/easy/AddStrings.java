package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/9.
 * 415
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        String longer = null, shorter = null;
        if (num1.length() >= num2.length()) {
            longer = num1;
            shorter = num2;
        } else {
            longer = num2;
            shorter = num1;
        }
        char[] sb = new char[5100];
        Arrays.fill(sb, '0');
        int carry = 0;
        int i = longer.length() - 1;
        int j = shorter.length() - 1;
        int ind = sb.length - 1;
        int temp = 0;
        for (; i >= 0 && j >= 0; i--, j--) {
            temp = longer.charAt(i) - 48 + shorter.charAt(j) - 48 + carry;
            if (temp >= 10) {
                temp = temp - 10;
                carry = 1;
            } else{
                carry = 0;
            }
            sb[ind--] = (char)(temp + 48);
        }
        for (;i >= 0; i--) {
            temp = longer.charAt(i) - 48 + carry;
            if (temp >= 10) {
                temp = temp - 10;
                carry = 1;
            } else {
                carry = 0;
            }
            sb[ind--] = (char)(temp + 48);
        }
        sb[ind--] = (char)(carry + 48);
        int nozero = sb.length - 1;
        for (int k = ind; k < sb.length; k++) {
            if (sb[k] != '0') {
                nozero = k;
                break;
            }
        }
        return new String(sb, nozero, sb.length - nozero );
    }

    public static void main(String[] args) {
        String num1 = "408";
        String num2 = "5";
        AddStrings as = new AddStrings();
        System.out.println(as.addStrings(num1, num2));
    }
}
