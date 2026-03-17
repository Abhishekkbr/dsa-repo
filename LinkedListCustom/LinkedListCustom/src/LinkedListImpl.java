

public class LinkedListImpl {
	Node head;
	static class Node{
		Node next;
		int data;
		
		public Node(int d) {
			this.data = d;
		}
	}
	
	public static LinkedListImpl insertData(int data, LinkedListImpl ll) {
		Node n = new Node(data);
		n.next = null;
		
		if(ll.head == null) {
			ll.head = n;
		}
		else {
			Node curr = ll.head;
			while(curr.next!=null) {
				curr = curr.next;
			}
			curr.next = n;
		}
		return ll;
	}
	
	public static void printLinkedList(LinkedListImpl ll) {
		Node curr = ll.head;
		while(curr!= null) {
			System.out.print(curr.data +"->");
			curr = curr.next;
		}
		System.out.println("NULL");
	}
	
	public static void main(String[] args) {
		LinkedListImpl ll = new LinkedListImpl();
		for (int i = 0; i<5 ;i++) {
			ll = insertData(i, ll);
		}
		printLinkedList(ll);
	}
}
