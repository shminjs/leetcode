package com.shminjs.leetcode.easy;

import java.util.HashMap;

/**
 * Created by shimin on 2017/6/9.
 * 447
 * Given n points in the plane that are all pairwise distinct a "boomerang" is a tuple of points (i, j, k) such that the distance between i
 * and j equals the distance between i and k (the order of the tuple matters).
 * Find the number of boomerangs, You may assume that n will at most 500 and coordinates of points are all in the range[-10000, 10000] (inclusive)
 */
public class NumberOfBoomeranges {
    public int numberOfBoomerangs(int[][] points) {
        HashMap<Integer, Integer> map = null;
        int x_t, y_t, result_t;
        Integer temp;
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    x_t = Math.abs(points[j][0] - points[i][0]);
                    y_t = Math.abs(points[j][1] - points[i][1]);
                    result_t = x_t * x_t + y_t * y_t;
                    temp = map.get(result_t);
                    if (temp == null) {
                        map.put(result_t, 1);
                    } else {
                        temp = temp + 1;
                        map.put(result_t, temp);
                    }
                }
            }
            for (Integer item : map.values()) {
                count += item * (item - 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        NumberOfBoomeranges nof = new NumberOfBoomeranges();
        System.out.println(nof.numberOfBoomerangs(points));
    }
}
