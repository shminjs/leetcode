package com.shminjs.leetcode.easy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shimin on 2017/6/9.
 * 401
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each Led represent a zero or one, with the least significant bit on the right.
 */
public class ReadBinaryWatch {
    private static int[] watchs = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};

    public List<String> readBinaryWatch(int num) {
        List<String> results = new ArrayList<>();
        int[] x = new int[watchs.length];
        Arrays.fill(x, -1);
        int k = 0;
        while (k >= 0) {
            while (x[k] < 1) {
                x[k] = x[k] + 1;
                int sum_re = sum(x);
                if (sum_re == num) {
                    // 得到一个解，可以继续
                    String result = generateTime(x);
                    if (result != null) results.add(result);
                    break;
                }
                if (k < x.length - 1 && sum_re < num) {
                    k = k + 1;
                }
            }
            x[k] = -1;
            k = k - 1;
        }
        return results;
    }

    private String generateTime(int[] x) {
        int hour = 0, miniute = 0;
        for (int i = 0; i < 4; i++) {
            if (x[i] == 1) hour += watchs[i];
        }
        if (hour >= 12) return null;
        for (int i = 4; i < x.length; i++) {
            if (x[i] == 1) miniute += watchs[i];
        }
        if (miniute >= 60) return null;
        StringBuilder sb = new StringBuilder(5);
        sb.append(Integer.toString(hour));
        sb.append(":");
        if (miniute < 10) {
            sb.append("0");
        }
        sb.append(miniute);
        return sb.toString();
    }

    private int sum(int[] x) {
        int result = 0;
        for (int i = 0; i < x.length; i++)
            if (x[i] > 0) result += x[i];
        return result;
    }

    public static void main(String[] args) {
        ReadBinaryWatch rbw = new ReadBinaryWatch();
        List<String> results = rbw.readBinaryWatch(2);
        System.out.println(results.toString());
    }
}
