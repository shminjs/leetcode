package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/14.
 * 374
 * We are playing the Guess Game. The game is as follows.
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong. I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1 or 0).
 */
public class GuessNumber {
    public int guessNumber(int n) {
        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int v = guess(mid);
            if (v > 0) lo = mid + 1;
            else if (v < 0) hi = mid - 1;
            else return mid;
        }
        return -1;
    }

    // 仅仅是为了不让上面的函数出现红色
    private int guess(int n) {
        return 0;
    }
}
