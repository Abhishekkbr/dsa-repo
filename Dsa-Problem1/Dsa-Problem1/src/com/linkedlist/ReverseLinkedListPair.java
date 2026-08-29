package com.linkedlist;

public class ReverseLinkedListPair {
    static class ListNode {
        int data;
        ListNode next;

        ListNode(int data) { this.data = data; }
    }
    public static void main(String[] args) {
        // build 1 → 2 → 3 → 4 → 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ReverseLinkedListPair sol = new ReverseLinkedListPair();
        ListNode result = sol.swapPairs(head);

        while (result != null) {
            System.out.print(result.data);
            if (result.next != null) {
                System.out.print("->");
            }
            result = result.next;
        }
        // Output: 2 → 1 → 4 → 3 → 5
    }

    private ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode first = head;
        ListNode second = head.next;

        first.next = swapPairs(second.next);
        second.next = first;

        return second;
    }
}
