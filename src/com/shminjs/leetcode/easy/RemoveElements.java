package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/16.
 * 203
 * Remove all elements from a linked list of integers that have value val.
 */
public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = null;
        ListNode p = head;
        while (p != null) {
            if (p.val == val) {
                if (pre != null) {
                    pre.next = p.next;
                } else {
                    head = p.next;
                }
            } else {
                pre = p;
            }
            p = p.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        ListNode k = new RemoveElements().removeElements(l, 1);
        while (k != null) {
            System.out.println(k.val);
            k = k.next;
        }
    }
}
