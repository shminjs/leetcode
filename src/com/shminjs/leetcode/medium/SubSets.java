package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/7/10.
 * Given a set of distinct integers, nums, return all possible subsets
 */
public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        int N = nums.length;
        for (int i = 0; i < (1 << N); i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    tmp.add(nums[j]);
                }
            }
            res.add(tmp);
        }
        return res;
    }
}
