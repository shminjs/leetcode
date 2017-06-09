package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/9.
 * 541
 * Given a string and an integer k, you need to reverse the first k character for every 2k character counting from the start of the string.
 * If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then
 * reverse the first k characters and the left the other as original.
 */
public class ReverseStr {
    public String reverseStr(String s, int k) {
        int N = s.length();
        StringBuilder sb = new StringBuilder(N);
        boolean flag = true;
        for (int i = 0; i < N; i += k) {
            if (flag) {
                int end = i + k < N ? i+k-1:N-1;
                for (int j = end; j >= i; j--) {
                    sb.append(s.charAt(j));
                }
                flag = false;
            } else {
                for (int j = i; j < N && j < i + k; j++) {
                    sb.append(s.charAt(j));
                }
                flag = true;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        ReverseStr rs = new ReverseStr();
        System.out.println(rs.reverseStr(s, 2));
    }
}
