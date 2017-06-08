package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/8.
 * 121
 * Say you have an array for which the i th element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock).
 * design an algorithm  to find the maximum profit.
 * do see the answer.
 */
public class MaxProfit1 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int minPro = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            minPro = Math.max(minPro, prices[i] - minPrice);
        }
        return minPro;
    }

    public static void main(String[] args) {
//        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices = {2, 1, 4};
        MaxProfit1 mp1 = new MaxProfit1();
        System.out.println(mp1.maxProfit(prices));
    }
}
