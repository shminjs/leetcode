package com.shminjs.leetcode.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/11/26.
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        int len = t.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int begin = -1;
        int left = 0;
        int end = s.length();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                int num = map.get(ch);
                if (num > 0) {
                    count++;
                }
                map.put(ch, num - 1);
            }
            while (count == len) {
                if (map.containsKey(s.charAt(left))) {
                    if (i + 1 - left < end - begin) {
                        begin = left;
                        end = i + 1;
                    }
                    map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                    if (map.get(s.charAt(left)) > 0) count--;
                    left++;
                } else {
                    left++;
                }
            }
        }
        if (begin != -1) {
            return s.substring(begin, end);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinWindow().minWindow("ADOBECODEBANC", "ABC"));
    }
}
