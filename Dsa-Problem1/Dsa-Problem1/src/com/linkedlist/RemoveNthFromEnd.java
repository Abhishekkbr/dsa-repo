package com.linkedlist;

public class RemoveNthFromEnd {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        RemoveNthFromEnd solution = new RemoveNthFromEnd();
        ListNode result = solution.removeNthFromEnd(head, 2);
        printList(result);   // 1 -> 2 -> 3 -> 5 -> null
    }

    private static void printList(ListNode result) {
        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
        System.out.println("null");
    }

    private ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        for (int i = 0 ; i <= n ; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }
}
