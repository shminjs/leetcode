package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/12.
 * 33
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * You are given a target to search if found in the array, return its index, otherwise return -1.
 * You may assume no duplicates exists in the array.
 */
public class Search {
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int start, int end, int target) {
        if (start <= end) {
            int ind1 = -1, ind2 = -1;
            while (start <= end) {
                if (nums[start] <= nums[end]) {
                    int ind = binarysearch(nums, start, end, target);
                    return ind;
                }

                int mid = start + (end - start) / 2;
                if (nums[mid] >= nums[start]) {
                    ind1 = binarysearch(nums, start, mid, target);
                    ind2 = search(nums, mid+1, end, target);
                } else {
                    ind1 = binarysearch(nums, mid, end, target);
                    ind2 = search(nums, start, mid-1, target);
                }
                if (ind1 != -1)  return ind1;
                if (ind2 != -1) return ind2;
                return -1;
            }
            return -1;
        } else {
            return -1;
        }
    }

    private int binarysearch(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Search search = new Search();
        System.out.println(search.search(new int[]{5, 1, 3}, 1));
    }
}
