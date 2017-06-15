package com.shminjs.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/6/15.
 * Given two strings and t, determine if they are isomorphic.
 * Two strings are isomorphic if the character in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may
 * map to the same character but a character may map to itself.
 */
public class IsIsomorphic {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map1.containsKey(s.charAt(i))) {
                // 包含该key
                if (map1.get(s.charAt(i)) != t.charAt(i)) return false;
            } else {
                // 不包含该key
                map1.put(s.charAt(i), t.charAt(i));
            }
            if (map2.containsKey(t.charAt(i))) {
                if (map2.get(t.charAt(i)) != s.charAt(i)) return false;
            } else {
                map2.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;
    }
}
