package com.shminjs.leetcode.medium;

import java.util.Arrays;

/**
 * Created by shimin on 2017/9/17.
 * 567
 */
public class CheckInclusion {
    public boolean checkInclusion(String s1, String s2) {
        int[] ss1 = new int[26];
        int[] ss2 = new int[26];
        boolean flag = false;
        for (int i = 0; i < s1.length(); i++) ss1[s1.charAt(i)-'a']++;
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            for (int j = i; j < i + s1.length(); j++) {
                ss2[s2.charAt(j)-'a']++;
            }
            if (Arrays.equals(ss1, ss2)) {
                flag = true;
                break;
            }
            Arrays.fill(ss2, 0);
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(new CheckInclusion().checkInclusion("adc", "dcda"));
    }
}
