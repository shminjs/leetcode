package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/16.
 * 88
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as on sorted array.
 *
 * 答案挺赞，倒着来。
 */
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m+n];
        int ind = 0;
        int k1 = 0, k2 = 0;
        while (k1 < m && k2 < n) {
            if (nums1[k1] < nums2[k2]) {
                tmp[ind++] = nums1[k1++];
            } else {
                tmp[ind++] = nums2[k2++];
            }
        }
        while (k1 < m) {
            tmp[ind++] = nums1[k1++];
        }
        while (k2 < n) {
            tmp[ind++] = nums2[k2++];
        }
        System.arraycopy(nums1, 0, tmp, 0, m+n);
    }
}
