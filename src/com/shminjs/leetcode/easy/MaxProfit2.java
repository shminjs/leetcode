package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/8.
 * 122
 * Say you have an array for which the i th element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many as you like (ie, buy one and sell one shell of the
 * stock multiples). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you
 * buy again).
 */
public class MaxProfit2 {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; ) {
            int temp = prices[i] - prices[i-1];
            if (temp > 0)
                profit += temp;
        }
        return profit;
    }
}


//public int maxProfit(int[] prices) {
//    int maxCur = 0, maxSoFar = 0;
//    for(int i = 1; i < prices.length; i++) {
//        maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
//        maxSoFar = Math.max(maxCur, maxSoFar);
//    }
//    return maxSoFar;
//}
