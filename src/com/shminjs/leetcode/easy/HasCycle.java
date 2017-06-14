package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/14.
 * 141
 * Given a linked list, determine if it has a cycle in it.
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        boolean flag = false;
        ListNode p1 = head;
        ListNode p2 = head.next;
        while (p1 != null && p2 != null) {
            if (p1.val == p2.val) {
                flag = true;
                break;
            }
            p1 = p1.next;
            if (p2.next == null) {
                break;
            } else {
                p2 = p2.next.next;
            }
        }
        return flag;
    }
}
