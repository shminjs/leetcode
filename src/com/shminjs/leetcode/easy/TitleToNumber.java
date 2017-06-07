package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/7.
 * 171
 * Related to question Excel Sheet Column Title.
 */
public class TitleToNumber {

    public int titleToNumber(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = sum * 26 + (s.charAt(i) - 64);
        }
        return sum;
    }

    public static void main(String[] args) {
        TitleToNumber ttn = new TitleToNumber();
        System.out.println(ttn.titleToNumber("AB"));
    }
}
