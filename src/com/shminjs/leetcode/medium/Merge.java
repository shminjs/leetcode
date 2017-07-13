package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by shimin on 2017/7/13.
 * 56
 */
public class Merge {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });
        int i = 0;
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        int N = intervals.size();
        while (i < N) {
            if (i + 1 < N && end >= intervals.get(i+1).start) {
                i++;
                end = Math.max(end, intervals.get(i).end);
            } else {
                res.add(new Interval(start, end));
                i++;
                if (i < N) {
                    start = intervals.get(i).start;
                    end = intervals.get(i).end;
                }
            }
        }
        return res;
    }
}

class Interval {
    int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
 }
