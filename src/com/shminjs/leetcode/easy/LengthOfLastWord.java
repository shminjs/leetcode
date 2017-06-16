package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/16.
 * 58
 * Given a string s consists of upper/lower-case alphabets and empty space character ' ', return the length of last word in the string.
 * If the last word is not exist, return 0.
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int last = s.length();
        int begin = -1;
        boolean flag = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (flag) last = i;
                else {
                    begin = i;
                    break;
                }
            } else {
                flag = false;
            }
        }
        return last-begin-1;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLastWord().lengthOfLastWord("b   a    "));
    }
}
