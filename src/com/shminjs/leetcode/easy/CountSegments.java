package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/13.
 * 434
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
 * Please not that the string does not contain any non-printable characters.
 */
public class CountSegments {
    public int countSegments(String s) {
        if (s.length() == 0) return 0;
        int result = 0;
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                flag = false;
            } else {
                if (!flag) result++;
                flag = true;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new CountSegments().countSegments(", , , ,        a, eaefa"));
    }
}
