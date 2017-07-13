package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimin on 2017/7/13.
 * 228
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Integer pre = null;
        int start = 0, end = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (pre == null) {
                start = nums[i];
                end = nums[i];
                pre = nums[i];
            } else if (pre + 1 == nums[i]) {
                end = nums[i];
                pre = nums[i];
            } else {
                if (start == end) {
                    res.add("" + start);
                } else {
                    res.add("" + start + "->" + end);
                }
                start = nums[i];
                end = nums[i];
                pre = nums[i];
            }
        }
        if (start == end) res.add("" + start);
        else res.add("" + start + "->" + end);
        return res;
    }
}
