package com.heap;

import java.util.PriorityQueue;


//O(N log K)  n = total number of nodes, k = number of linked lists
public class Merge2SortedArray {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // Sample test case: lists = [[1,4,5],[1,3,4],[2,6]]
        ListNode[] lists = new ListNode[3];

        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);

        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);

        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);

        ListNode result = mergeKLists(lists);
        printList(result);
    }

    private static void printList(ListNode result) {
        while (result != null) {
            System.out.print(result.val + " → ");
            result = result.next;
        }
        System.out.println("null");
    }

    private static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode ll : lists) {
            if (ll != null) {
                queue.add(ll);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while(!queue.isEmpty()) {
            ListNode heapNode = queue.poll();
            current.next = heapNode;
            current = current.next;
            if(heapNode.next != null) {
                queue.offer(heapNode.next);
            }
        }
        return dummy.next;
    }
}
