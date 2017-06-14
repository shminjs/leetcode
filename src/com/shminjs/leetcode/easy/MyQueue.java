package com.shminjs.leetcode.easy;

import java.util.LinkedList;


/**
 * Created by shimin on 2017/6/14.
 * 232
 * Implement the following operations of a queue using stacks.
 *  push(x)-Push element x to the back of queue
 *  pop()-Removes the element from in front of queue
 *  peek()-Get the front element
 *  empty()-Return whether the queue is empty
 */
public class MyQueue {
    private LinkedList<Integer> stack = null;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.offer(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.poll();
    }

    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}
