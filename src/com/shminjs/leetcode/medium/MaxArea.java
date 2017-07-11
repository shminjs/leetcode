package com.shminjs.leetcode.medium;

import java.util.ResourceBundle;

/**
 * Created by shimin on 2017/7/11.
 * 11
 * Given n non-negative integers a1, a2, ..., an where each represents a point at cordinate (i, ai). n vertical line are drawn such
 * that two point of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container
 * contain the most water.
 * Note: You may not slant the container and n is at least 2.
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int ans = 0;
        while (start < end) {
            int v = (end - start) * Math.min(height[start], height[end]);
            if (v > ans) ans = v;
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return ans;
    }
}
