package com.shminjs.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimin on 2017/6/14.
 * 119
 * Given an index k, return the k th row of the Pascal's triangle.
 */
public class GetRow {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> results = new ArrayList<>();
        int nCk = 1;
        for (int k = 0; k <= rowIndex; k++) {
            results.add(nCk);
            nCk = (int)(1.0* nCk * (rowIndex - k) / (k + 1));
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(new GetRow().getRow(32).toString());
    }
}
