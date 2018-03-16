package com.shminjs.leetcode.test;

import java.util.ArrayList;

/**
 * Created by shimin on 2018/3/15.
 */
public class FindContinusSequence {
    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum <= 1) return res;
        int[] preSum = new int[(sum+1)/2+1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + i;
        }
        for (int i = preSum.length - 1; i >= 0; i--) {
            if (preSum[i] >= sum) {
                // TODO: 可能存在
                int left = binarySearch(preSum, preSum[i] - sum);
                if (left >= 0) {
                    // 存在，生成
                    ArrayList<Integer> sub =  new ArrayList<>();
                    for (int j = left + 1; j <= i; j++) {
                        sub.add(j);
                    }
                    res.add(sub);
                }
            } else {
                // TODO: 已经不存在了，可以直接退出
                break;
            }
        }
        return res;
    }

    private int binarySearch(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == k) return mid;
            else if (nums[mid] < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FindContinusSequence().findContinuousSequence(3));
    }
}
