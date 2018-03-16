package com.shminjs.leetcode.medium;

import com.sun.xml.internal.fastinfoset.algorithm.FloatEncodingAlgorithm;

/**
 * Created by shimin on 2017/7/15.
 * 152
 */
public class MaxProduct {
    public int maxProduct(int[] nums) {
//        int max_so_far = nums[0];
//        int max_ending_here = nums[0];
//        int max_en_1 = nums[0];
//        boolean flag = false;
//        if (max_en_1 < 0) flag = true;
//        Integer max_en_2 = null;
//        for (int i = 1; i < nums.length; i++) {
//            max_ending_here = Math.max(max_ending_here * nums[i], nums[i]);
//            if (flag) {
//                if (max_en_2 == null) max_en_2 = nums[i];
//                else max_en_2 = max_en_2 * nums[i];
//            }
//            if (max_en_1 == 0) {
//                max_en_1 = nums[i];
//                max_en_2 = null;
//                flag = false;
//            } else {
//                max_en_1 = max_en_1 * nums[i];
//            }
//            if (!flag && nums[i] < 0) {
//                flag = true;
//            }
//            max_so_far = Math.max(max_so_far, Math.max(max_ending_here, max_en_1));
//            if (flag && max_en_2 != null) { max_so_far = Math.max(max_so_far, max_en_2); }
//        }
//        return max_so_far;
        int max = nums[0];
        int maxP = 1;
        int maxN = 1;
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                maxN = 1;
                maxP = 1;
                max = Math.max(0, max);
                flag = true;
                continue;
            } else {
                maxP *= nums[i];
                maxN *= nums[i];
                if (nums[i] < 0 && flag) {
                    maxN = 1;
                    flag = false;
                }
                max = Math.max(max, Math.max(maxP, maxN));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaxProduct().maxProduct(new int[]{1, 0, -1, 2, 3, -5, -2}));
    }
}
