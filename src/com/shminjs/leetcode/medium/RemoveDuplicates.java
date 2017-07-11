package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/11.
 * 80
 * Follow up for "Remove Duplicates"
 * What if duplicates are allowed at most twice?
 * For Example
 * Given sorted array nums = [1, 1, 1, 2, 2, 3]
 * Your function should return length = 5, with first five of nums being 1, 1, 2, 2, 3. It doesn't matter what you leave beyond the new length
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int id = 0;
        int count = 0;
        Integer pre = null;
        for (int i = 0; i < nums.length; i++) {
            if (pre != null) {
                if (pre == nums[i]) count++;
                else {
                    count = 1;
                }
            } else {
                count = 1;
            }
            pre = nums[i];
            if (count <= 2) nums[id++] = nums[i];
        }
        return id;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        System.out.println(removeDuplicates.removeDuplicates(nums));
    }
}
