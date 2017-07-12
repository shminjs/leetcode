package com.shminjs.leetcode.medium;

import sun.font.TrueTypeFont;

import javax.xml.stream.events.StartDocument;
import java.awt.font.NumericShaper;

/**
 * Created by shimin on 2017/7/12.
 * 81
 * Search in Rotated Sorted Array II
 */
public class Search2 {
    public boolean search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private boolean search(int[] nums, int start, int end, int target) {
        if (start <= end) {
            boolean ind1 = false, ind2 = false;
            if (nums[start] < nums[end]) {
                boolean ind = binarysearch(nums, start, end, target);
                return ind;
            }
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;
            ind1 = search(nums, start, mid-1, target);
            ind2 = search(nums, mid+1, end, target);
            return ind1 || ind2;
        } else {
            return false;
        }
    }

    private boolean binarysearch(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Search2 search2 = new Search2();
        System.out.println(search2.search(new int[]{1, 3, 1, 1, 1}, 3));
    }
}
