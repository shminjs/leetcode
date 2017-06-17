package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/17.
 * 605
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent
 * plots-they would compete for water and both would die.
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n
 * new flowers can be plated in it without violating the no-adjacent-flowers rule.
 */
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        boolean first = true;
        for (int i = 0;i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                count++;
            } else {
                if (first && count > 0) {
                    // 前置0，计算
                    n -= count / 2;
                    count = 0;
                } else {
                    n -= (count - 1) / 2;
                    count = 0;
                }
                first = false;
            }
        }
        if (first && count > 0) {
            n -= (count + 1) / 2;
        } else if (count > 0) {
            n -= count / 2;
        }
        return n <= 0;
    }

    public static void main(String[] args) {
        System.out.println(new CanPlaceFlowers().canPlaceFlowers(new int[]{0, 1, 0}, 1));
    }
}
