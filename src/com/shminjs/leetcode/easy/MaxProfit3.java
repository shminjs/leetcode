package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/10/30.
 */
public class MaxProfit3 {
    public int maxProfit(int[] prices, int fee) {
        int minPrice = 50000;
        int maxPrice = 0;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                if (maxPrice - minPrice > 2) {
                    res += maxPrice - minPrice - 2;
                    maxPrice = 0;
                    minPrice = 50000;
                    continue;
                }
                minPrice = prices[i];
            }
            if (prices[i] > maxPrice) {
                maxPrice = prices[i];
            } else {
                if (maxPrice - minPrice > 2) {
                    res += maxPrice - minPrice - 2;
                    maxPrice = 0;
                    minPrice = 50000;
                    continue;
                }
            }
        }
        if (maxPrice - minPrice > 2) {
            res += maxPrice - minPrice - 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxProfit3().maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }
}
