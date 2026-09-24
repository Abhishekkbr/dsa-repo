package com.linkedlist;

public class IntersectionOfLinkedLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // build shared tail: c1 -> c2 -> c3
        ListNode c1 = new ListNode(8);
        ListNode c2 = new ListNode(4);
        ListNode c3 = new ListNode(5);
        c1.next = c2;
        c2.next = c3;

        // list A: a1 -> a2 -> c1 -> c2 -> c3
        ListNode a2 = new ListNode(1);
        ListNode a1 = new ListNode(4);
        a1.next = a2;
        a2.next = c1;

        // list B: b1 -> b2 -> b3 -> c1 -> c2 -> c3
        ListNode b3 = new ListNode(4);
        ListNode b2 = new ListNode(1);
        ListNode b1 = new ListNode(5);
        b1.next = b2;
        b2.next = b3;
        b3.next = c1;

        ListNode intersection = getIntersectionNode(a1, b1);
        System.out.println(intersection != null ? "Intersection at node with value: " + intersection.val : "No intersection");
        // Output: Intersection at node with value: 8  (node c1)
    }

    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        while (pointerA != pointerB) {
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }
        return pointerA;
    }
}
