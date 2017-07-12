package com.shminjs.leetcode.medium;

import java.util.Arrays;

/**
 * Created by shimin on 2017/7/12.
 * 16
 */
public class ThreeSumCloses {
    public int threeSumClosest(int[] nums, int target) {
        Integer result = null;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i + 1, hi = nums.length - 1;
                while (lo < hi) {
                    int tmp = nums[i] + nums[lo] + nums[hi];
                    if (result == null) {
                        result = tmp;
                    } else if (Math.abs(tmp - target) < Math.abs(result - target)) result = tmp;
                    if (tmp < target) lo++;
                    else if (tmp > target) hi--;
                    else break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSumCloses().threeSumClosest(new int[]{1, 1, -1, -1, 3}, -1));
    }
}
