package com.shminjs.leetcode.hard;

import org.w3c.dom.css.Counter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/8/25.
 * 76
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        int count = t.length(), begin = 0, end = 0, d = Integer.MAX_VALUE, head = 0;
        while (end < s.length()) {
            if (map.containsKey(s.charAt(end)) && map.get(s.charAt(end)) > 0) {
                count--;
            }
            if (map.containsKey(s.charAt(end))) {
                map.put(s.charAt(end), map.get(s.charAt(end))-1);
                end++;
            }
            while (count == 0) {
                if (end - begin < d) {
                    d = end - begin;
                    head = begin;
                }
                if (map.containsKey(s.charAt(begin)) && map.get(s.charAt(begin)) == 0) {
                    count++;
                }
                if (map.containsKey(s.charAt(begin))) {
                    map.put(s.charAt(begin), map.get(s.charAt(begin))+1);
                    begin++;
                }
            }
        }
        return d == Integer.MAX_VALUE ? "" : s.substring(head, head + d);
    }

    public static void main(String[] args) {
        System.out.println(new MinWindow().minWindow("a", "aa"));
    }
}
