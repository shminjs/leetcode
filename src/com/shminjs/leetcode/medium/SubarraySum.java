package com.shminjs.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/7/7.
 *
 */
public class SubarraySum {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] sums = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sums[i] = (i-1 >= 0 ? sums[i-1] : 0) + nums[i];
        }
        map.put(0, 1);
        for (int i = 0; i < sums.length; i++) {
            if (map.containsKey(sums[i] - k)) {
                count += map.get(sums[i] - k);
            }
            map.put(sums[i], map.getOrDefault(sums[i], 0) + 1);
        }
        return count;
    }
}
