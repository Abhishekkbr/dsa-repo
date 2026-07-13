
public class DoublyLinkedList {
	static class Node{
		Node next;
		Node previous;
		int data;
		
		public Node(int data) {
			this.data = data;
		}
	}
	
	Node head, tail = null;
	
	public void addNode(int data) {
		Node n = new Node(data);
		
		if(head == null) {
			head = tail = n;
			tail.next = null;
			head.previous = null;
		}else {
			tail.next = n;
			n.previous = tail;
			tail = n;
			tail.next = null;
		}
	}
	public void printNode() {
		Node curr = head;
		if(head == null) {
			System.out.println("Empty list");
			return;
		}
		System.out.println("Nodes of list: ");
		while(curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
	}
	 public static void main(String[] args) {  
	        //create a DoublyLinkedList object
	        DoublyLinkedList dl_List = new DoublyLinkedList();  
	        //Add nodes to the list  
	        dl_List.addNode(10);  
	        dl_List.addNode(20);  
	        dl_List.addNode(30);  
	        dl_List.addNode(40);  
	        dl_List.addNode(50);  
	   
	        //print the nodes of DoublyLinkedList  
	        dl_List.printNode();  
	    }  
}
