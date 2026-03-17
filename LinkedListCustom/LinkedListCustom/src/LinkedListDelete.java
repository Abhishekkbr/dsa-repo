
public class LinkedListDelete {

	class Node{
		Node next;
		int data;
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	Node head = null;
	Node tail = null;

	public void addNode(int data) {
		Node curr = new Node(data);

		if(head == null) {
			head = curr;
			tail = curr;
		}else {
			tail.next = curr;
			tail = curr;		
		}
	}

	public void deleteNodeFromStart() {

		if(head == null) {
			System.out.println("Linked List is empty");
		}else {
			if(head != tail) {
				head = head.next;
			}
			else {
				head = tail = null;
			}
		}
	}

	public void display() {  
		//Node current will point to head  
		Node current = head;  
		if(head == null) {  
			System.out.println("List is empty");  
			return;  
		}  
		while(current != null) {  
			//Prints each node by incrementing pointer  
			System.out.print(current.data + " ");  
			current = current.next;  
		}  
		System.out.println();  
	}  
	public static void main(String[] args) {
		LinkedListDelete sList = new LinkedListDelete();  

		//Adds data to the list  
		sList.addNode(1);  
		sList.addNode(2);  
		sList.addNode(3);  
		sList.addNode(4);  

		//Printing original list  
		System.out.println("Original List: ");  
		sList.display();  

		while(sList.head != null) {  
			sList.deleteNodeFromStart();  
			//Printing updated list  
			System.out.println("Updated List: ");  
			sList.display();  
		}  
	}  
}

