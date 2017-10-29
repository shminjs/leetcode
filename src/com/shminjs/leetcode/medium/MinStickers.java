package com.shminjs.leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by shimin on 2017/10/8.
 * 691
 */
public class MinStickers {
    public int minStickers(String[] stickers, String target) {
        Map<String, Integer> memo = new HashMap<>();
        memo.put("", 0);
        int[] targetmap = new int[26];
        for (int i = 0; i < target.length(); i++) { targetmap[target.charAt(i)-'a']++; }
        Map<Integer, int[]> stickersmap = new HashMap<>();
        for (int i = 0; i < stickers.length; i++) {
            int[] letters = new int[26];
            for (int j = 0; j < stickers[i].length(); j++) {
                letters[stickers[i].charAt(j)-'a']++;
            }
            stickersmap.put(i, letters);
        }

        int res = minStickers(stickersmap, stickers.length, target, memo);
        return res;
    }

    private int minStickers(Map<Integer, int[]> stickersmap, int m, String target, Map<String, Integer> memo) {
        // base case
        if (memo.containsKey(target)) return memo.get(target);

        // Initialize result
        int res = Integer.MAX_VALUE;

        int[] letters = new int[26];
        for (char c : target.toCharArray()) letters[c-'a']++;

        // Try every coin that has smaller value than V
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (letters[j] > 0) {
                    for (int k = 0; k < Math.max(0, letters[j]-stickersmap.get(i)[j]); k++) {
                        sb.append((char)('a'+j));
                    }
                }
            }
            String s = sb.toString();
            if (s.length() < target.length()) {
                int sub_res = minStickers(stickersmap, m, s, memo);
                if (sub_res != -1 && sub_res + 1 < res) {
                    res = sub_res + 1;
                }
            }
        }
        memo.put(target, res == Integer.MAX_VALUE ? -1 : res);
        return memo.get(target);
    }

    public static void main(String[] args) {
        String[] stickers = {"all","chord","doctor","dance","drive","ready","phrase","skill","dress","select","if","develop","space","broad","lone","was","fight","how","window","place","has","plural","star","complete","though","rub","practice","here","nation","dark","job","observe","key","hole","short","last","neck","oh","science","industry","work","gun","rule","magnet","stead","many","push","tall","soft","road"};
        System.out.println(new MinStickers().minStickers(stickers, "thosecontinent"));
    }
}
