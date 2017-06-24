package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/24.
 * 125
 * Given a string, determine if it is a palindrome. Considering only alphaumeric character and ignore cases.
 */
public class IsPalindrome2 {
    public boolean isPalindrome(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            char st = s.charAt(start), en = s.charAt(end);
            if (isAlphanumeric(st) && isAlphanumeric(en)) {
                if (Character.isUpperCase(st)) st = Character.toLowerCase(st);
                if (Character.isUpperCase(en)) en = Character.toLowerCase(en);
                if (st != en) return false;
                start++;
                end--;
            } else if (!isAlphanumeric(st)) {
                start++;
            } else {
                end--;
            }
        }
        return true;
    }

    private boolean isAlphanumeric(char ch) {
        return Character.isAlphabetic(ch) || Character.isDigit(ch);
    }

    public static void main(String[] args) {
        System.out.println(new IsPalindrome2().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new IsPalindrome2().isPalindrome("race a car"));
    }
}
