package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/7/10.
 * 39
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        int[] x = new int[candidates.length];
        Arrays.fill(x, -1);
        int k = 0;
        while (k >= 0) {
            while (x[k] * candidates[k] < target) {
                x[k] = x[k] + 1;
                int sum_re = sum(x, candidates);
                if (sum_re == target) {
                    // 得到一个解
                    res.add(generate(x, candidates));
                    break;
                }
                if (k < x.length - 1 && sum_re < target) {
                    k = k + 1;
                }
            }
            x[k] = -1;
            k = k - 1;
        }
        return res;
    }

    private int sum(int[] x, int[] candiates) {
        int result = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] > 0) result += x[i] * candiates[i];
        }
        return result;
    }

    private List<Integer> generate(int[] x, int[] candiate) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i]; j++) {
                res.add(candiate[i]);
            }
        }
        return res;
    }
}
