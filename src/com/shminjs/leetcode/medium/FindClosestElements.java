package com.shminjs.leetcode.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shimin on 2017/9/18.
 * 658
 */
public class FindClosestElements {
    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        List<Integer> res = new LinkedList<>();
        int ind = Collections.binarySearch(arr, x);
        if (ind >= 0) {
            // 找到了x
            res.add(x);
            k--;
            add(arr, k, x, ind-1, ind+1, res);
        } else {
            // 没有找到x
            ind = -(ind+1);
            add(arr, k, x, ind-1, ind, res);
        }
        return res;
    }

    private void add(List<Integer> arr, int k, int x, int left, int right, List<Integer> res) {
        while (k > 0) {
            if (left >= 0 && right < arr.size()) {
                if (Math.abs(arr.get(left)-x) <= Math.abs(arr.get(right)-x)) {
                    res.add(0, arr.get(left));
                    left--;
                } else {
                    res.add(arr.get(right));
                    right++;
                }
                k--;
            } else if (left >= 0) {
                res.add(0, arr.get(left));
                left--;
                k--;
            } else if (right >= 0) {
                res.add(arr.get(right));
                right++;
                k--;
            } else if (left < 0 || right >= arr.size()) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(new Integer[]{0, 0, 1, 2, 3, 3, 4, 7, 7, 8});
        System.out.println(new FindClosestElements().findClosestElements(arr, 3, 5));
    }
}
