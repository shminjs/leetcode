package com.shminjs.leetcode.medium;

import sun.misc.resources.Messages_pt_BR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shimin on 2017/7/11.
 * 90
 */
public class SubsetWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void backtracking(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start) {
        res.add(new ArrayList<>(tmp));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue;
            tmp.add(nums[i]);
            backtracking(res, tmp, nums, i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}
