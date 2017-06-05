package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/5.
 * 557
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 */
public class ReverseWords {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        int begin = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || i == s.length() - 1) {
                int end = i == s.length() - 1 ? i : i-1;
                for (int j = end; j >= begin; j--) {
                    sb.append(s.charAt(j));
                }
                if (i != s.length() - 1) {
                    sb.append(' ');
                }
                begin = i + 1;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "Let's take LeetCode contest";
        ReverseWords re = new ReverseWords();
        String output = re.reverseWords(input);
        System.out.println(output);
    }
}
