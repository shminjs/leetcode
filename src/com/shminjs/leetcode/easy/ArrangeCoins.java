package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/13.
 * 441
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of 32-big signed integer.
 */
public class ArrangeCoins {
    public int arrangeCoins(int n) {
        int start = 0;
        int end = n;
        int mid = 0;
        while (start <= end){
            mid = (start + end) >>> 1;
            if ((0.5 * mid * mid + 0.5 * mid ) <= n){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start - 1;
    }

    public static void main(String[] args) {
        System.out.println(new ArrangeCoins().arrangeCoins(1804289383));
        System.out.println(Integer.MAX_VALUE);
    }
}
