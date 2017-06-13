package com.shminjs.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimin on 2017/6/12.
 * 118
 * Given numRows, generate the first numRows of Pascal's triangle.
 */
public class Generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>(numRows);
        for (int i = 1; i <= numRows; i++) {
            List<Integer> temp = new ArrayList<>(i);
            List<Integer> prev = null;
            if (i > 1) prev = results.get(i-1-1);
            temp.add(1);
            for (int j = 1; j < i - 1; j++) {
                temp.add(prev.get(j) + prev.get(j-1));
            }
            if (i > 1) temp.add(1);
            results.add(temp);
        }
        return results;
    }

    public static void main(String[] args) {
        Generate g = new Generate();
        g.generate(5);
    }
}
