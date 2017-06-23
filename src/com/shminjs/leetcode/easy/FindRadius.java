package com.shminjs.leetcode.easy;

import java.util.Arrays;

/**
 * Created by shimin on 2017/6/23.
 * 475
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius all the houses.
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that ll houses
 * could be covered by those heaters.
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius
 * standard of heaters.
 */
public class FindRadius {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < houses.length; i++) {
            int ind = Arrays.binarySearch(heaters, houses[i]);
            if (ind < 0) {
                ind = -(ind + 1);
            }
            int dist1 = ind - 1 >= 0 ? houses[i] - heaters[ind-1]:Integer.MAX_VALUE;
            int dist2 = ind < heaters.length ? heaters[ind] - houses[i]:Integer.MAX_VALUE;

            max = Math.max(max, Math.min(dist1, dist2));
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new FindRadius().findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
//        System.out.println(Arrays.binarySearch(new int[]{1,2,3,4}, 5));
    }
}
