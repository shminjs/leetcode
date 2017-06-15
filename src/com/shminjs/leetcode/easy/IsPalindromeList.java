package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/15.
 * 234
 * Given a singly linked list, determine if it is palindrome.
 */
public class IsPalindromeList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        boolean flag = true;
        int ind = 0;
        ListNode p1 = head;
        while (p1 != null) {
            ind++;
            p1 = p1.next;
        }
        if (ind == 1) return true;
        ListNode p2 = head;
        int k = 0;
        if (ind % 2 == 0) k = ind / 2;
        else k = ind / 2 + 1;
        while (p2 != null) {
            k--;
            head = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = head;
            if (k == 0) break;
        }
        ListNode p3 = p1, p4 = p2;
        if (ind % 2 == 1) p3 = p3.next;
        while (p3 != null && p4 != null) {
            if (p3.val != p4.val) {
                flag = false;
                break;
            }
            p3 = p3.next;
            p4 = p4.next;
        }
        while (p1 != null) {
            head = p1.next;
            p1.next = p2;
            p2 = p1;
            p1 = head;
        }
        return flag;
    }

    public static void main(String[] args) {
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(2);
        l.next.next.next.next = new ListNode(1);
        System.out.println(new IsPalindromeList().isPalindrome(l));
    }
}
