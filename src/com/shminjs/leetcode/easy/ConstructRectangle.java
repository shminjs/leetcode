package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/6.
 * 492
 * For a web developer, it is very important to know how to design a web page's size. So, given a specific rectangular web page's area, your
 * job by now is to design a rectangualr web page., whose length L and width satisfy the following requirements:
 * 1. The area of the rectangular web page you designed must equal to the given target area.
 * 2. The width W should not be larger than the Length L, which means L >= W.
 * 3. The difference between length L and width W should be small as possible.
 * You need to output the length L and width W of the web page you designed in sequence.
 */
public class ConstructRectangle {
    public int[] constructRectangle(int area) {
        int N = (int)Math.sqrt(area);
        if (N * N == area) {
            return new int[]{N, N};
        } else {
            for (int i = N + 1; i <= area; i++) {
                int W = area / i;
                if ((area - i * W) == 0) {
                    return new int[]{i, W};
                }
            }
        }
        return null;
    }
}
