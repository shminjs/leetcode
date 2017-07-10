package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shimin on 2017/7/10.
 * 380
 * Design a data structure that supports all following operations in average O(1) time.
 * insert(val)
 * remove(val)
 * getRandom
 */
public class RandomizedSet {
    private Map<Integer, Integer> kvmap = null;
    private Map<Integer, Integer> vkmap = null;
    private List<Integer> vs = null;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        kvmap = new HashMap<>();
        vkmap = new HashMap<>();
        vs = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!kvmap.containsKey(val)) {
            vs.add(val);
            kvmap.put(val, vs.size()-1);
            vkmap.put(vs.size()-1, val);
            return true;
        } else {
            return false;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (kvmap.containsKey(val)) {
            int ind = kvmap.get(val);
            int vlast = vkmap.get(vs.size()-1);
            vs.set(ind, vlast);
            kvmap.put(vlast, ind);
            vkmap.put(ind, vlast);
            kvmap.remove(val);
            vkmap.remove(vs.size()-1);
            vs.remove(vs.size()-1);
            return true;
        } else {
            return false;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int N = vs.size();
        int ind = (int)(Math.random() * N);
        return vs.get(ind);
    }
}
