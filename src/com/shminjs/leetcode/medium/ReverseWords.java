package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/11/22.
 */
public class ReverseWords {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 1);
        int ind = s.length() - 1;
        int end = s.length();
        while (ind >= 0) {
            if (s.charAt(ind) != ' ') {
                // 减去
                while (ind >= 0 && s.charAt(ind) != ' ') {
                    ind--;
                }
                sb.append(s.substring(ind + 1, end)).append(" ");
            } else {
                end = ind;
                ind--;
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWords().reverseWords("   a   b  c d   e  "));
    }
}
