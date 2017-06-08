package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/8.
 * 237
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to the node.
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list
 * should become 1 -> 2 -> 4 after calling you function.
 */
public class DeleteNode {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

 // Definition for singly-linked list.
 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
