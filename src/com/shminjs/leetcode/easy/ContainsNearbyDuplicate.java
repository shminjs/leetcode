package com.shminjs.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/6/16.
 * 219
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
 * and the absolute difference between i and j is at most k.
 */
public class ContainsNearbyDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int ind = map.get(nums[i]);
                if (i - ind <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return  false;
    }
}
