package com.shminjs.leetcode.test;

/**
 * Created by shimin on 2017/11/4.
 */
public class HelloSearch {
    public int search(int[] nums, int target) {
        int p = -1;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            if (nums[lo] <= nums[hi]) {
                p = Math.max(p, binarysearch(nums, lo, hi, target));
                break;
            }

            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == nums[hi]) {
                p = mid;
                break;
            } else if (nums[mid] < nums[hi]){
                p = Math.max(p, binarysearch(nums, mid, hi, target));
                hi = mid - 1;
            } else {
                p = Math.max(p, binarysearch(nums, lo, mid, target));
                lo = mid + 1;
            }
        }
        return p;
    }

    private int binarysearch(int[] nums, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new HelloSearch().search(new int[]{2, 3, 4, 5, 6, 7, 1}, 1));
    }
}

