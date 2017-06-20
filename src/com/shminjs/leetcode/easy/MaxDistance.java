package com.shminjs.leetcode.easy;

import java.util.List;

/**
 * Created by shimin on 2017/6/20.
 * 624
 * Given m arrays, and each array is sorted in ascending order. Now you can pick up two integer from two different arrays (each array picks one)
 * and calculate the distance. We define the distance between two integers a and b to be their absolute difference \a-b\. You task is to find the maximum
 * distance.
 */
public class MaxDistance {
    public int maxDistance(List<List<Integer>> arrays) {
        int min1 = 1001, min1_ind = -1, min2 = 1001, min2_ind = -1;
        int max1 = -1001, max1_ind = -1, max2 = -1001, max2_ind = -1;
        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i).get(0)  <= min1) {
                min2 = min1;
                min2_ind = min1_ind;
                min1 = arrays.get(i).get(0);
                min1_ind = i;
            } else if (arrays.get(i).get(0) <= min2) {
                min2 = arrays.get(i).get(0);
                min2_ind = i;
            }
            if (arrays.get(i).get(arrays.get(i).size()-1) >= max1) {
                max2 = max1;
                max2_ind = max1_ind;
                max1 = arrays.get(i).get(arrays.get(i).size()-1);
                max1_ind = i;
            } else if (arrays.get(i).get(arrays.get(i).size()-1) >= max2) {
                max2 = arrays.get(i).get(arrays.get(i).size()-1);
                max2_ind = i;
            }
        }
        if (min1_ind != max1_ind) {
            return Math.abs(max1 - min1);
        } else {
            if (min2_ind != -1 && max2_ind != -1) {
                return Math.max(Math.abs(max1 - min2), Math.abs(max2 - min1));
            } else if (min2_ind != -1) {
                return Math.abs(max1 - min2);
            } else {
                return Math.abs(max2 - min1);
            }
        }
    }
}
