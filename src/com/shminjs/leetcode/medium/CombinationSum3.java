package com.shminjs.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/7/7.
 * 216
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each
 * combination should be a unique set of numbers.
 */
public class CombinationSum3 {
    private static int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<>();
        int[] x = new int[nums.length];
        Arrays.fill(x, -1);
        int s = 0;
        while (s >= 0) {
            while (x[s] < 1) {
                x[s] = x[s] + 1;
                int count_re = count(x);
                int sum_re = sum(x);
                if (count_re == k && sum_re == n) {
                    // 得到一个解，放入res中
                    res.add(generate(x));
                    break;
                }
                if (s < x.length - 1 && count_re < k && sum_re < n) {
                    s = s + 1;
                }
            }
            x[s] = -1;
            s = s - 1;
        }
        return res;
    }

    private List<Integer> generate(int[] x) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < x.length; i++) {
            if (x[i] == 1) res.add(nums[i]);
        }
        return res;
    }

    private int sum(int[] x) {
        int result = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] > 0) result += nums[i];
        }
        return result;
    }

    private int count(int[] x) {
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] > 0) sum += 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        CombinationSum3 cs = new CombinationSum3();
        List<List<Integer>> res = cs.combinationSum3(3, 7);
    }
}
