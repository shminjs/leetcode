package com.shminjs.leetcode.test;

/**
 * Created by shimin on 2017/12/3.
 */
public class MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int N) {
        char[] nums = Integer.toString(N).toCharArray();
        char pre = '0';
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] < pre) {
                break;
            }
            pre = nums[i];
        }
        if (i == nums.length) return N;
        i--;
        for (; i >= 0; ) {
            if (i-1 >= 0 && nums[i] == nums[i-1]) {
                i--;
            } else {
                break;
            }
        }
        int res = 0;
        for (int j = 0; j < i; j++) {
            res = res * 10 + nums[j] - '0';
        }
        res = res * 10 + nums[i] - '0' - 1;
        for (int j = i + 1; j < nums.length; j++) {
            res = res * 10 + 9;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MonotoneIncreasingDigits().monotoneIncreasingDigits(10));
    }
}
