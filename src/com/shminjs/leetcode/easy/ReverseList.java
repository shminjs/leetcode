package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/9.
 * 206
 * Reverse a single linked list.
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        int n = 0;
        ListNode pointer = head;
        while (pointer != null) {
            n++;
            pointer = pointer.next;
        }
        int[] vals = new int[n];
        pointer = head;
        n = 0;
        while (pointer != null) {
            vals[n++] = pointer.val;
            pointer = pointer.next;
        }
        pointer = head;
        while (pointer != null) {
            pointer.val = vals[--n];
            pointer = pointer.next;
        }
        return head;
    }
}
