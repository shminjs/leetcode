package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/9.
 * 551
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 * 1. 'A': absent.
 * 2. 'B': Late
 * 3. 'P':Present.
 */
public class CheckRecord {
    public boolean checkRecord(String s) {
        int a_c = 0;
        int l_c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                a_c++;
                l_c = 0;
            } else if (s.charAt(i) == 'L') {
                l_c++;
            } else {
                l_c = 0;
            }
            if (a_c > 1 || l_c > 2) return false;
        }
        return true;
    }
}
