package com.shminjs.leetcode.medium;

import com.shminjs.leetcode.easy.TrailingZeroes;
import com.sun.deploy.resources.Deployment_ja;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimin on 2017/7/11.
 * 120
 */
public class MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        int m = triangle.size();
        int res = Integer.MAX_VALUE;
        int save = 0;
        for (int i = 0; i < triangle.size(); i++) {
            int n = triangle.get(i).size();
            for (int j = 0; j < n; j++) {
                int left = j - 1 >= 0 ? save : Integer.MAX_VALUE;
                int up = (i == 0 || j < n - 1) ? dp[j] : Integer.MAX_VALUE;
                save = dp[j];
                dp[j] = Math.min(left, up) + triangle.get(i).get(j);
                if (i == m-1 && dp[j] < res) res = dp[j];
            }
            save = 0;
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> tmp1 = new ArrayList<>();
        tmp1.add(-1);
        List<Integer> tmp2 = new ArrayList<>();
        tmp2.add(3);
        tmp2.add(2);
        List<Integer> tmp3 = new ArrayList<>();
        tmp3.add(-3);
        tmp3.add(1);
        tmp3.add(-1);
        triangle.add(tmp1);
        triangle.add(tmp2);
        triangle.add(tmp3);
        MinimumTotal minimumTotal = new MinimumTotal();
        System.out.println(minimumTotal.minimumTotal(triangle));
    }
}
