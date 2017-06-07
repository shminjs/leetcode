package com.shminjs.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by shimin on 2017/6/7.
 * 349
 * Given two arrays, wreite a function to compute their intersection.
 */
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        HashSet<Integer> results = new HashSet<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else {
                results.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] arr = new int[results.size()];
        int ind = 0;
        for (Integer item : results) {
            arr[ind++] = item;
        }
        return arr;
    }
}
