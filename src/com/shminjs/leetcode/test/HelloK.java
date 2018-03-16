package com.shminjs.leetcode.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by shimin on 2017/8/31.
 */
public class HelloK {
    public int longestSubSumK(int[] sn, int K) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};
        int runningSum = 0;
        int max = 0;
        for (int i=0;i<sn.length;i++) {
            runningSum += sn[i];
            if (K != 0) runningSum %= K;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) {
                    max = Math.max(max, i-prev);
                }
            }
            else map.put(runningSum, i);
        }
        return max;
    }
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int[] sn = new int[N];
//        for (int i = 0; i < N; i++) {
//            sn[i] = sc.nextInt();
//        }
//        int K = sc.nextInt();
//        System.out.println(new HelloK().longestSubSumK(sn, K));
        char[][] arr = new char[3][3];
        System.out.println(arr[0][0] == '\0');
    }
}
