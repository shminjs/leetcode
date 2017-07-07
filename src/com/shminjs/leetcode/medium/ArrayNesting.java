package com.shminjs.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shimin on 2017/7/7.
 * 565
 * A zero-indexed array A consisting of N different integers is given. The array contains all integers in the range[0, N-1].
 * Sets S[k] for 0 <= k < N are defined follows.
 * S[K] = {A[K], A[A[K], A[A[A[K]]}
 * 效率堪忧
 */
public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                Set<Integer> tmp = new HashSet<>();
                tmp.add(nums[i]);
                int ind = nums[i];
                while(!tmp.contains(nums[ind])) {
                    tmp.add(nums[ind]);
                    ind = nums[ind];
                }
                max = Math.max(max, tmp.size());
                set.addAll(tmp);
            }
        }
        return max;
    }
}
