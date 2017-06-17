package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/17.
 * 14
 * Write a function to find the longest common prefix amongst an array of strings.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("");
        int ind = 0;
        while (detect(strs, ind)) {
            sb.append(strs[0].charAt(ind));
            ind++;
        }
        return sb.toString();
    }

    private boolean detect(String[] strs, int ind) {
        if (strs.length == 1 && ind >= strs[0].length()) {
            return false;
        }
        for (int i = 1; i < strs.length; i++) {
            if (ind < strs[i].length() && ind < strs[i-1].length()) {
                if (strs[i].charAt(ind) != strs[i-1].charAt(ind))
                    return false;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strs = {"", "b"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
    }
}
