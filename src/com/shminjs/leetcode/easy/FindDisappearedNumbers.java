package com.shminjs.leetcode.easy;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by shimin on 2017/6/6.
 * 448
 * Given an array of integer where 1<=a[i]<=n(n=size of array), some elements appear twice and other appear once.
 * Find all the elements of [1..n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the return list does not count as extra space.
 */
public class FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> al = new ArrayList<>();
        int ind;
        for (int i = 0; i < nums.length; i++) {
            ind = Math.abs(nums[i]) - 1;
            nums[ind] = -Math.abs(nums[ind]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                al.add(i + 1);
            }
        }
        return al;
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 2, 7, 8, 2, 3, 1};
        int result = 0;
        for (int i = 0; i < 8; i++) {
            result |= array[i];
        }
        for (int i = 1; i < 9; i++) {
            System.out.println(result & i);
        }
    }
}
