package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/17.
 * 160
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * 最高解答真的强。
 */
public class GetIntersecionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = a == null ? headB:a.next;
            b = b == null ? headA:b.next;
        }
        return a;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(5);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        ListNode headB = new ListNode(1);
        headB.next = new ListNode(2);
        headB.next.next = new ListNode(3);
        ListNode result = new GetIntersecionNode().getIntersectionNode(headA, headB);
        while (headA != null) {
            System.out.println(headA.val);
            headA = headA.next;
        }
        while (headB != null) {
            System.out.println(headB.val);
            headB = headB.next;
        }
        while (result!=null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
