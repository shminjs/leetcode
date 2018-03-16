package com.shminjs.leetcode.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/12/3.
 */
public class DeleteAndEarn {
    int res = 0;
    public int deleteAndEarn(int[] nums) {
        // 暴力回溯试试效果
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + num);
        }
        dfs(map, 0);
        return res;
    }

    private void dfs(Map<Integer, Integer> map, int sum) {
        if (map.size() == 0) {
            res = Math.max(res, sum);
        } else {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Map<Integer, Integer> m2 = new HashMap<>(map);
                m2.remove(entry.getKey());
                if (m2.containsKey(entry.getKey()-1)) {
                    m2.remove(entry.getKey()-1);
                }
                if (m2.containsKey(entry.getKey()+1)) {
                    m2.remove(entry.getKey()+1);
                }
                dfs(m2, sum + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new DeleteAndEarn().deleteAndEarn(new int[]{3,7,10,5,2,4,8,9,9,4,9,2,6,4,6,5,4,7,6,10}));
    }
}
