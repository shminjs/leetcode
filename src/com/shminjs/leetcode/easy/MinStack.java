package com.shminjs.leetcode.easy;

import java.util.PriorityQueue;

/**
 * Created by shimin on 2017/6/24.
 * 155
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 */
public class MinStack {
    private int[] pq;       // store items at indices 1 to n
    private int n;          // number of items on priority stack.
    /** initialize your data structure here. */
    public MinStack() {
        this(1);
    }

    private MinStack(int iniCapacity) {
        pq = new int[iniCapacity + 1];
        n = 0;
    }

    public void push(int x) {
        if (n == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain stack invariant.
        pq[++n] = x;
    }

    public void pop() {
        n--;
    }

    public int top() {
        return pq[n];
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            min = Math.min(min, pq[i]);
        }
        return min;
    }

    private void resize(int capacity) {
        int[] temp = new int[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-1);
        for (int i = 0; i < 3; i++) {
            System.out.println(ms.getMin());
        }
    }
}
