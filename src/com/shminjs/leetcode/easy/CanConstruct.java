package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/7.
 * 383
 * Given an arbitrary ransom note string and another containing letters from all the magazines, write a function that will return
 * true if the ransom note can be constructed from the magazines; otherwise, it will return false.
 * Each letter in the magazine string can only be used once in your ransom note.
 */
public class CanConstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] n_l = new int[26];
        int[] m_l = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            n_l[ransomNote.charAt(i)-97]++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            m_l[magazine.charAt(i)-97]++;
        }
        for (int i = 0; i < 26; i++) {
            if (m_l[i] < n_l[i]) return false;
        }
        return true;
    }
}
