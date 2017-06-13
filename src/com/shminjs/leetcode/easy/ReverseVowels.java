package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/12.
 * 345
 * Write a function that takes a string as input and reverse only the vowels of a string.
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        String po = "AaEeIiOoUu";
        char[] temp = new char[s.length()];
        int last = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (po.indexOf(s.charAt(i)) == -1) {
                temp[i] = s.charAt(i);
            } else {
                last = find(po, s, last) - 1;
                temp[i] = s.charAt(last+1);
            }
        }
        return new String(temp);
    }

    private int find(String po, String s, int last) {
        int i;
        for (i= last; i >= 0; i--) {
            if (po.indexOf(s.charAt(i)) != -1) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        ReverseVowels rv = new ReverseVowels();
        System.out.println(rv.reverseVowels(s));
    }
}
