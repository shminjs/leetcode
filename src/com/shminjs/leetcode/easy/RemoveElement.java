package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/11.
 * 27
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of element can be changed. It doesn't matter what you leave beyond the new length.
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int p1 = -1;
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] == val) {
                if (p1 == -1) p1 = i;
            } else {
                if (p1 != -1) {
                    nums[p1] = nums[i];
                    p1++;
                }
            }
        }
        if (p1 == -1) return nums.length;
        else return p1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        RemoveElement re = new RemoveElement();
        System.out.println(re.removeElement(nums, 3));
    }
}
