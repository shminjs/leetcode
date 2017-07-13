package com.shminjs.leetcode.medium;

import sun.util.resources.cldr.ms.CurrencyNames_ms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shimin on 2017/7/13.
 * 229
 * Given an integer array of size n, find all elements that appear more than n/3 times. The algorithm should run
 * int linear time and in O(1) space.
 */
public class MajorityElement {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1) {
                count1++;
            } else if (nums[i] == number2) {
                count2++;
            } else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1) {
                count1++;
            } else if (nums[i] == number2) {
                count2++;
            }
        }
        if (count1 > len / 3) res.add(number1);
        if (count2 > len / 3) res.add(number2);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MajorityElement().majorityElement(new int[]{1}).toString());
    }
}
