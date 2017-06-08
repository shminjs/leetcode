package com.shminjs.leetcode.easy;

import java.util.HashMap;

/**
 * Created by shimin on 2017/6/8.
 * 13
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class RomanToInt {
    private static HashMap<Character, Integer> map = new HashMap<>();
    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public int romanToInt(String s) {
        int result = 0;
        int ind = 0;
        while (ind < s.length()) {
            if (s.charAt(ind) == 'I' || s.charAt(ind) == 'X' || s.charAt(ind) == 'C') {
                if (ind + 1 < s.length()) {
                    if (map.get(s.charAt(ind)) < map.get(s.charAt(ind+1))) {
                        result += map.get(s.charAt(ind+1)) - map.get(s.charAt(ind));
                        ind += 2;
                        continue;
                    }
                }
            }
            result += map.get(s.charAt(ind));
            ind += 1;
        }
        return result;
    }

    public static void main(String[] args) {
//        String s = "DCXXI";
        String s= "MCMXCVI";
        RomanToInt rti = new RomanToInt();
        System.out.println(rti.romanToInt(s));
    }
}
