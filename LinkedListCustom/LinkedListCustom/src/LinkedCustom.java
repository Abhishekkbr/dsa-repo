import java.util.LinkedList;
public class LinkedCustom {
	
	Node head;
	
	class Node{
		Node next;
		int data;
		
		public Node(int d){
			this.data = d;
			this.next = null;
		}
	}
	
	void printMiddle() {
		Node slow_ptr = head;
		Node fast_ptr = head;
		if(head != null) {
			while(fast_ptr != null && fast_ptr.next != null) {
				fast_ptr = fast_ptr.next.next;
				slow_ptr = slow_ptr.next;
			}
			System.out.println("The middle element is : "+slow_ptr.data);
		}
	}
	
	public void push(int data) {
		Node n = new Node(data);
		//n.next = null;
		if(head == null) {
			head = n;
		}else {
			Node curr = head; 
			while(curr.next != null) {
				curr = curr.next;
			}
			curr.next = n;
		}
	}
	
	public void printList() {
		Node curr = head;
		while(curr!=null) {
			System.out.print(curr.data +"->");
			curr = curr.next;
		}
		System.out.println("NULL");
	}
	
	public static void main(String[] args) {
		LinkedCustom ll = new LinkedCustom();
		for(int i=5; i>0; i--) {
			ll.push(i);
			ll.printList();
			ll.printMiddle();
			
		}
	}
}
