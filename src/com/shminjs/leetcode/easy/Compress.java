package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/10/29.
 */
public class Compress {
    public int compress(char[] chars) {
        if (chars.length <= 1) return chars.length;
        Character cmp = null;
        int count = 0;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (cmp != null) {
                if (chars[i] == cmp) {
                    count++;
                }
                if (chars[i] != cmp) {
                    chars[index++] = cmp;
                    if (count != 1) {
                        // handler count number.
                        String nums = Integer.toString(count);
                        for (int j = 0; j < nums.length(); j++) {
                            chars[index++] = nums.charAt(j);
                        }
                    }
                    cmp = chars[i];
                    count = 1;
                }
            } else {
                cmp = chars[i];
                count = 1;
            }
            if (i == chars.length - 1) {
                chars[index++] = cmp;
                if (count != 1) {
                    // handler count number.
                    String nums = Integer.toString(count);
                    for (int j = 0; j < nums.length(); j++) {
                        chars[index++] = nums.charAt(j);
                    }
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        char[] chars = new char[]{'a', 'a', 'b', 'c', 'c'};
        System.out.println(new Compress().compress(chars));
        System.out.println(Arrays.toString(chars));
    }
}
