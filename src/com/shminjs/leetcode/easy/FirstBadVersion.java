package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/24.
 * 278
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product
 * fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n version [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which will return version is bad. Implement a function function to find the first bad version.
 * You should minimize the number of calls to the API.
 */
public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int lo = 1, hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (isBadVersion(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean isBadVersion(int k) {
        return false;
    }
}
