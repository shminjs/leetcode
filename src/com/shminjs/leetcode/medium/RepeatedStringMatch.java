package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimin on 2017/10/1.
 * 686
 */
public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == B.charAt(0)) {
                // 可以开始判断
                int tmp = 1;
                int pa = i;
                int pb = 0;
                int count = 0;
                while (count < B.length()) {
                    if (A.charAt(pa) != B.charAt(pb)) {
                        tmp = 0;
                        break;
                    }
                    if (pa + 1 >= A.length() && count + 1 < B.length()) tmp++;
                    if (pb + 1 >= B.length() && count + 1 < B.length()) tmp++;
                    pa = (pa + 1) % A.length();
                    pb = (pb + 1) % B.length();
                    count++;
                }
                if (tmp != 0) {
                    res = Math.min(res, tmp);
                }
            }
        }
        if (res != Integer.MAX_VALUE) {
            return res;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new RepeatedStringMatch().repeatedStringMatch("aa",  "aaaaaaa"));
    }
}