package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/10.
 * 83
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode pointer1 = head;
        ListNode pointer2 = head.next;
        while (pointer1 != null && pointer2 != null) {
            if (pointer1.val == pointer2.val) {
                if (pointer2.next == null)
                    pointer1.next = null;
            } else {
                pointer1.next = pointer2;
                pointer1 = pointer2;
            }
            pointer2 = pointer2.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(1);
        l.next.next = new ListNode(2);
        l.next.next.next = new ListNode(3);
        l.next.next.next.next = new ListNode(3);
        DeleteDuplicates dp = new DeleteDuplicates();
        ListNode head = dp.deleteDuplicates(l);
        while (l != null) {
            System.out.println(l.val);
            l = l.next;
        }
    }
}
