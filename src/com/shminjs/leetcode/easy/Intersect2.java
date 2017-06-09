package com.shminjs.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by shimin on 2017/6/9.
 * 350
 * Given two arrays, write a function to compute their intersection.
 */
public class Intersect2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> results = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            }
            else if (nums1[i] > nums2[j]) {
                j++;
            }
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

    public static void main(String[] args) {
        int[] nums1 = {1,2, 2, 1};
        int[] nums2 = {2, 2};
        Intersect2 i2 = new Intersect2();
        System.out.println(i2.intersect(nums1, nums2).toString());
    }
}
