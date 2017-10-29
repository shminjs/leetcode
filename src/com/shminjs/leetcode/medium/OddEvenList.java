package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/9/4.
 * 328
 */
public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode oddP = head;
        ListNode evenL = head.next;
        ListNode evenP = evenL;
        if (evenL == null || evenL.next == null) return head;
        ListNode p = evenL.next;
        evenL.next = null;
        boolean flag = true;
        while (p != null) {
            if (flag) {
                oddP.next = p;
                oddP = p;
            } else {
                evenP.next = p;
                evenP = p;
            }
            flag = !flag;
            p = p.next;
        }
        oddP.next = evenL;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        new OddEvenList().oddEvenList(listNode);
    }
}
