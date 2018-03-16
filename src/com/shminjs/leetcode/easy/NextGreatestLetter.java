package com.shminjs.leetcode.easy;

import jdk.nashorn.internal.ir.WhileNode;

/**
 * Created by shimin on 2017/12/10.
 */
public class NextGreatestLetter {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, h = letters.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (letters[m] == target) {
                l = m + 1;
            } else if (letters[m] < target) {
                h = m - 1;
            } else {
                // letters[m] > target
                l = m + 1;
            }
        }
        if (l == letters.length) l = 0;
        return letters[l];
    }

    public static void main(String[] args) {
        System.out.println(new NextGreatestLetter().nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'd'));
    }
}
