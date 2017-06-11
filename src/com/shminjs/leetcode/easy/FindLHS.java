package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/11.
 * 594
 * We define a harmonious array is an array where the difference between its maximum value and tis minimum value is exactly 1.
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequances.
 */
public class FindLHS {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int pre = -1;
        int count = 1;
        int maxsofar = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                count++;
            } else if (nums[i] - nums[i-1] == 1) {
                if (pre == -1) {
                    pre = count;
                    count = 1;
                } else {
                    maxsofar = Math.max(pre+count, maxsofar);
                    pre = count;
                    count = 1;
                }
            } else {
                if (pre != -1) maxsofar = Math.max(pre+count, maxsofar);
                pre = -1;
                count = 1;
            }
        }
        if (pre != -1) {
            maxsofar = Math.max(pre+count, maxsofar);
        }
        return maxsofar;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
//        int[] nums = {1, 3, 2, 3, 5, 2, 3, 7};
//        int[] nums = {1, 1, 1, 1};
//        int[] nums = {1, 1, 2, 2};
//        int[] nums = {1, 3, 5, 7, 9, 11, 13, 15, 17};
        FindLHS flhs = new FindLHS();
        System.out.println(flhs.findLHS(nums));
    }
}
