package com.shminjs.leetcode.test;

import java.util.Scanner;

/**
 * Created by shimin on 2017/9/13.
 */
public class HelloWin {
    public boolean win(int[] nums) {
        if (nums.length < 3) return true;
        else return predict(nums, 1, 0 + nums[0], 0, false) || predict(nums, 2, 0 + nums[1] + nums[2], 0, false);
//        int[][] dp = new int[nums.length][3];
//        dp[0][1] = nums[0];
//        int sum = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            sum += nums[i];
//            if (dp[i-1][0] != 0) {
//                dp[i][1] = dp[i-1][0] + nums[i];
//            }
//            if (dp[i-1][1] != 0) {
//                dp[i][0] = dp[i-1][1];
//                dp[i][2] = dp[i-1][1] + nums[i];
//            }
//            if (dp[i-1][2] != 0) {
//                dp[i][0] = Math.max(dp[i][0], dp[i-1][2]);
//            }
//        }
//        int N = nums.length;
//        int res =  Math.max(Math.max(dp[N-1][0], dp[N-1][1]),dp[N-1][2]);
//        if (res > sum / 2) return true;
//        else return false;
    }

    private boolean predict(int[] nums, int start, int ca, int cb, boolean flag) {
        if (start >= nums.length) {
            // 选择完毕
            if (ca > cb) return true;
            else return false;
        } else {
            if (flag) {
                if (start + 1 < nums.length) {
                    return predict(nums, start + 1, ca + nums[start], cb, false) ||
                            predict(nums, start + 2, ca + nums[start] + nums[start+1], cb, false);
                } else {
                    return predict(nums, start, ca + nums[start], cb, false);
                }
            } else {
                return predict(nums, start + 1, ca, cb + nums[start], true) &&
                        (start + 1 >= nums.length || predict(nums, start + 2, ca,cb + nums[start] + nums[start+1], true));
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] sn = new int[N];
        for (int i = 0; i < N; i++) {
            sn[i] = sc.nextInt();
        }
        System.out.println(new HelloWin().win(sn));
    }
}
