package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/31.
 * 142
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != null || slow != null) {
            if (fast == slow) break;
            else {
                if (fast == null || fast.next == null || fast.next.next == null) fast = null;
                else fast = fast.next.next;
                if (slow == null || slow.next == null) {
                    slow = null;
                } else {
                    slow = slow.next;
                }
            }
        }
        if (fast == null) return null;
        else {
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
     }
 }
