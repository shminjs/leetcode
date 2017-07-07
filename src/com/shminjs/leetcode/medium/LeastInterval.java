package com.shminjs.leetcode.medium;

import java.util.Arrays;

/**
 * Created by shimin on 2017/7/7.
 * 621
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in on interval. For each interval, CPU could finish one task or just be
 * idle.
 * However, there is non-negative cooling interval n that means between sam tasks, there must be at least n intervals that CPU are doing different
 * tasks or just be idle.
 * You need to return the least number of intervals the CUP will be finish all the given tasks.
 */
public class LeastInterval {
    public int leastInterval(char[] tasks, int n) {
        int[] letters = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            letters[tasks[i] - 'A']++;
        }
        Arrays.sort(letters);
        int i = 25;
        while (i >= 0 && letters[i] == letters[25]) i--;
        return Math.max(tasks.length, (letters[25] - 1) * (n + 1) + 25 - i);
    }

    public static void main(String[] args) {
        System.out.println(new LeastInterval().leastInterval(new char[]{'A','A','A','B','B','B', 'C', 'C', 'D'
        }, 1));
    }
}
