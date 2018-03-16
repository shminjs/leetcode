package com.shminjs.leetcode.hard;

/**
 * Created by shimin on 2017/11/8.
 */
public class Jump {
        public int jump(int[] nums) {
            if (nums.length < 2) return 0;
            int begin = 1, end = Math.min(nums[0] + 0, nums.length - 1);
            int ans = 1;
            while (end != nums.length - 1) {
                int max = Integer.MIN_VALUE;
                for (int i = begin; i <= end; i++) {
                    max = Math.max(max, Math.min(nums[i] + i, nums.length - 1));
                }
                begin = end + 1;
                end = max;
                ans++;
            }
            return ans;
        }

    public static void main(String[] args) {
        System.out.println(new Jump().jump(new int[]{2, 3, 1, 1, 4}));
    }
}
