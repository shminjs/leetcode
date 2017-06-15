package com.shminjs.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/6/15.
 * 290
 * Given a pattern and a string str, find if str follows the same pattern.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();

        if (pattern.length() != strs.length) return false;
        for (int i = 0; i < pattern.length(); i++) {
            if (map1.containsKey(pattern.charAt(i))) {
                if (!map1.get(pattern.charAt(i)).equals(strs[i]))
                    return false;
            } else {
                map1.put(pattern.charAt(i), strs[i]);
            }
            if (map2.containsKey(strs[i])) {
                if (map2.get(strs[i]) != pattern.charAt(i))
                    return false;
            } else {
                map2.put(strs[i], pattern.charAt(i));
            }
        }
        return true;
    }
}
