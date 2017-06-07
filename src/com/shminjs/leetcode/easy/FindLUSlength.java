package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 521
 * Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings. The longest uncommon
 * subsequence is defined as the longest of one of these string and this subsequence should not be any subsequence of the other strings.
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the
 * remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.
 * The input will be two strings, and the output needs to the length of longest uncommon subsequence. If the longest uncommon subsequnece doesn't
 * exist, return -1.
 */
public class FindLUSlength {
    public int findLUSlength(String a, String b) {
        String l;
        if (a.length() == b.length()) {
            if (a.equals(b)) {
                return -1;
            } else {
                return a.length();
            }
        } else {
            if (a.length() > b.length()) {
                l = a;
            } else {
                l = b;
            }
            return l.length();
        }
    }

    public static void main(String[] args) {
        String a = "aefawfawfawfaw";
        String b = "aefawfawfawfaw";
        FindLUSlength fl = new FindLUSlength();
        System.out.println(fl.findLUSlength(a, b));
    }
}
