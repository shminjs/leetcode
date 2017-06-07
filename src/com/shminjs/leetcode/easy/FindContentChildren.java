package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/6.
 * 455
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie. Each
 * child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; and each cookie j has a size sj.
 * If sj >= gi, we can assign the cookie j to the child i, and the child will be content. Your goal is to maximum the number of your content
 * and output the maximum number.
 */
public class FindContentChildren {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int c_s = s.length - 1;
        int count = 0;
        for (int i = g.length-1; i >= 0; i--) {
            boolean flag = false;
            for (int j = c_s; j >= 0; j--) {
                if (s[j] >= g[i]) {
                    flag = true;
                    count++;
                    break;
                }
            }
            if (flag) c_s--;
            if (c_s >= s.length) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
    }
}
