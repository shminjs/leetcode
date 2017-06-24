package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/24.
 * 168
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 */
public class ConvertToTitle {
    private static String digits = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String convertToTitle(int n) {
        n = n - 1;
        StringBuilder sb = new StringBuilder();
        while (n >= 26) {
            int ind = n % 26;
            n = n / 26 - 1;
            sb.append(digits.charAt(ind));
        }
        sb.append(digits.charAt(n));
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new ConvertToTitle().convertToTitle(28));
    }
}
