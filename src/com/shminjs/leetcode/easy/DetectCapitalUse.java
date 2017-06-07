package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 520
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 * We define the usageof capitals in a world to be right when one of the following cases holds:
 * 1. All letters in this word are capitals, like "USA"
 * 2. All letters in this word are not capitals, like "leetcode"
 * 3. Only the first letters in this word is capital if it has more than one letter, like "Google"
 */
public class DetectCapitalUse {
    public boolean detectCapitalUse(String word) {
        int N = word.length();
        int count = 1;
        if (Character.isUpperCase(word.charAt(0))) {
            for (int i = 1; i < N; i++) {
                if (Character.isUpperCase(word.charAt(i))) count++;
                if (count != 1 && count != i + 1) return false;
            }
        } else {
            for (int i = 1; i < N; i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String word = "USA";
        DetectCapitalUse dcu = new DetectCapitalUse();
        System.out.println(dcu.detectCapitalUse(word));
    }
}
