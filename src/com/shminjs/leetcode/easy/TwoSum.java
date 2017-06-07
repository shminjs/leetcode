package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 167
 * Given an array of integers that is already sorted in asceding order, find two numbers such that they add up to a
 * specific target number. The function twoSum should return indices of the two numbers such that they add up to the
 * target, where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int ind = -1;
            if ((ind =binarySearch(numbers, i+1,target - numbers[i])) != -1) {
                return new int[] {i + 1, ind + 1};
            }
        }
        return null;
    }

    private static int binarySearch(int[] numbers, int low, int value) {
        int lo = low, hi = numbers.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi -lo)/2;
            if (value < numbers[mid]) hi = mid - 1;
            else if (value > numbers[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
