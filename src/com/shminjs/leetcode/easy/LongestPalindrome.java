package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/8.
 * 409
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] array = new int[58];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (array[s.charAt(i)-65] == 1) {
                count++;
                array[s.charAt(i)-65] = 0;
            } else {
                array[s.charAt(i)-65]++;
            }
        }
        for (int i = 0; i < 58; i++) {
            if (array[i] == 1)
                return count * 2 + 1;
        }
        return count * 2;
    }

    public static void main(String[] args) {
        String test = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        System.out.println(test.length());
        LongestPalindrome lp = new LongestPalindrome();
        System.out.println(lp.longestPalindrome(test));
    }
}
