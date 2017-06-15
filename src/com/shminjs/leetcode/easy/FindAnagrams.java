package com.shminjs.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shimin on 2017/6/15.
 * 438
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both String s and p will not be largeer than 20100.
 * The order of output does not matter.
 *
 * 答案是O(n)，想法很巧妙，值得一看。
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> results = new ArrayList<>();
        int N = p.length();
        for (int i = N-1; i < s.length(); i++) {
            if (isAnagram(s, i+1-N, i+1, p))
                results.add(i+1-N);
        }
        return results;
    }

    public boolean isAnagram(String s, int start, int end, String t) {
        int[] s_a = new int[26];
        int[] t_a = new int[26];
        for (int i = start; i < end; i++)
            s_a[s.charAt(i)-97]++;
        for(int i = 0; i < t.length(); i++)
            t_a[t.charAt(i)-97]++;
        for(int i = 0; i < 26; i++) {
            if (s_a[i] != t_a[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new FindAnagrams().findAnagrams("cbaebabacd", "abc").toString());
    }
}
