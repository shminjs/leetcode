package com.shminjs.leetcode.easy;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by shimin on 2017/6/7.
 * 506
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be award medals: "Gold
 * Medal", "Silver Medal" and "Bronze Medal".
 * 根据序号排序。
 */
public class FindRelativeRanks {
    public String[] findRelativeRanks(int[] nums) {
        int N = nums.length;
        int[] indexs = new int[N];
        String[] results = new String[N];
        for (int i = 0; i < N; i++) {
            indexs[i] = i;
        }
        quickSort(nums, indexs, 0, N - 1);
        if (0 < N) results[indexs[N-1]] = "Gold Medal";
        if (1 < N) results[indexs[N-2]] = "Silver Medal";
        if (2 < N) results[indexs[N-3]] = "Bronze Medal";
        for (int i = 3; i < N; i++) {
            results[indexs[N-1-i]] = Integer.toString(i+1);
        }
        return results;
    }

    private void quickSort(int[] nums, int[] indexs, int lo, int hi) {
        if (hi <= lo) return;
        int qvit = partition(nums, indexs, lo, hi);
        quickSort(nums, indexs, lo, qvit - 1);
        quickSort(nums, indexs, qvit + 1, hi);
    }

    private int partition(int[] nums, int[] indexs, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = nums[indexs[lo]];
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (nums[indexs[++i]] < v) if (i == hi) break;
            while (nums[indexs[--j]] > v) if (j == lo) break;
            if (i >= j) break;
            exch(indexs, i, j);
        }
        exch(indexs, lo, j);
        return j;
    }

    private void exch(int[] indexs, int v, int w) {
        int temp = indexs[v];
        indexs[v] = indexs[w];
        indexs[w] = temp;
    }

    public static void main(String[] args) {
        int[] array = {10, 3, 8, 9, 4};
        FindRelativeRanks frr = new FindRelativeRanks();
        String[] results = frr.findRelativeRanks(array);
        System.out.println(Arrays.toString(results));
    }
}
