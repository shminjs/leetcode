package com.shminjs.leetcode.medium;

import java.util.Arrays;

/**
 * Created by shimin on 2017/8/24.
 * 43
 */
public class Multiply {
    public String multiply(String num1, String num2) {
        String longer = num1.length() > num2.length() ? num1 : num2;
        String shorter = num1.length() <= num2.length() ? num1 : num2;
        int[] nums = new int[longer.length() + shorter.length()];
        for (int i = shorter.length() - 1; i >= 0; i--) {
            multiply(nums, (shorter.length() - i - 1), shorter.charAt(i), longer);
        }
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                start = i;
                break;
            }
        }
        if (start == 0 && nums[start] == 0) start = nums.length - 1;
        String res = "";
        for (int n : nums) res = res + Integer.toString(n);
        return res.substring(start, nums.length);
    }

    private void multiply(int[] nums, int end, char n, String num) {
        int acc = 0, p = nums.length - 1 - end;
        for (int i = num.length() - 1; i >= 0; i--) {
            int res = (num.charAt(i) - '0') * (n - '0');
            int d0 = res % 10, d1 = res / 10;
            int tmp = nums[p] + d0 + acc;
            acc = d1 + tmp / 10;
            nums[p] = tmp % 10;
            p--;
        }
        nums[p] = nums[p] + acc;
    }

    public static void main(String[] args) {
        System.out.println(new Multiply().multiply("1", "1"));
    }
}
