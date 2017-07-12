package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shimin on 2017/7/12.
 * 40
 * Given a collection of candidate numbers (C) and a target number (T). Find all unique combination in C where candidate numbers
 * sums to T.
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, new ArrayList<>(), candidates, 0, target);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tmp, int[] candidates, int start, int target) {
        if (target == 0) res.add(new ArrayList<>(tmp));
        else if (target < 0) return;
        else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i-1]) continue;
                tmp.add(candidates[i]);
                backtrack(res, tmp, candidates, i + 1, target - candidates[i]);
                tmp.remove(tmp.size()-1);
            }
        }
    }
}
