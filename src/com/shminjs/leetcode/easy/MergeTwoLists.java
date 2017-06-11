package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/11.
 * 21
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = null;
        ListNode pointer1 = null;
        ListNode pointer2 = null;
        if (l1.val < l2.val) {
            head = l1;
            pointer1 = l1.next;
            pointer2 = l2;
        } else {
            head = l2;
            pointer1 = l1;
            pointer2 = l2.next;
        }
        ListNode pointer3 = head;
        while (pointer1 != null && pointer2 != null) {
            if (pointer1.val < pointer2.val) {
                pointer3.next = pointer1;
                pointer3 = pointer1;
                pointer1 = pointer1.next;
            } else {
                pointer3.next = pointer2;
                pointer3 = pointer2;
                pointer2 = pointer2.next;
            }
        }
        if (pointer1 != null) pointer3.next = pointer1;
        if (pointer2 != null) pointer3.next = pointer2;
        return head;
    }
}
