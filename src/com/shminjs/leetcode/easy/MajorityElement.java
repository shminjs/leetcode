package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/8.
 * 169
 * Given an aray of size n, find the majority element. The majority element is the element is the element that appears more than [n/2] times.
 * You may assume the array is no-epmty and the majority elements always exist in the array.
 * 有O(n)解法
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int k = nums.length / 2;
        int lo = 0, hi = nums.length - 1;
        while (hi > lo) {
            int ind = partition(nums, lo, hi);
            if (k < ind) hi = ind - 1;
            else if (k > ind) lo = ind + 1;
            else return nums[ind];
        }
        return nums[lo];
    }

    private int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = nums[lo];
        while (true) {
            while (nums[++i] < v) if (i == hi) break;
            while (nums[--j] > v) if (j == lo) break;
            if (i >= j) break;
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }

    private void exch(int[] nums, int v, int w) {
        int temp = nums[v];
        nums[v] = nums[w];
        nums[w] = temp;
    }

    public static void main(String[] args){
        int[] array = {-1, 100, 2, 100, 100, 4, 100};
        MajorityElement me = new MajorityElement();
        System.out.println(me.majorityElement(array));
    }
}
