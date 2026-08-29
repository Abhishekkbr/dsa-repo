package com.linkedlist;

public class ReverseKGroup {
    static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) {
            this.data = data;
        }
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        ReverseKGroup solution = new ReverseKGroup();
        ListNode result = solution.reverseKGroup(head, 3);
        printList(result);   // 2 -> 1 -> 4 -> 3 -> 5 -> null
    }

    private static void printList(ListNode result) {
        while (result != null) {
            System.out.print(result.data + " -> ");
            result = result.next;
        }
        System.out.println("null");
    }

    private ListNode reverseKGroup(ListNode head, int k) {
       ListNode node = head;
       int count = 0;
       while (node != null && count < k) {
           node = node.next;
           count++;
       }

       if (count < k) {
           return head;
       }

       ListNode curr = head;
       ListNode prev = null;

       for (int i = 0 ; i < k ; i++) {
           ListNode next = curr.next;
           curr.next = prev;
           prev = curr;
           curr = next;
       }

       head.next = reverseKGroup(curr, k);

       return prev;
    }
}
