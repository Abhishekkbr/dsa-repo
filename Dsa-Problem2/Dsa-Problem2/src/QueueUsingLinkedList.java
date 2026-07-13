
public class QueueUsingLinkedList {

	static class Qnode{
		Qnode next;
		int data;

		Qnode(int data){
			this.data = data;
			this.next = null;
		}
	}

	static class CustomQueue{
		Qnode front,rear;

		public CustomQueue(){
			this.front=this.rear=null;
		}

		public void enqueue(int key) {
			Qnode q = new Qnode(key);
			if(this.rear == null) {
				this.rear = q;
				this.front = q;
				return;
			}
			this.front.next = q;
			this.front = q;
		}
		
		public void dequeue() {
			if(this.front == null)
				return;
			Qnode q = this.front;
			this.front = this.front.next;
			
			if(this.front == null)
				this.rear = null;
		}
	}
	
	public static void main(String[] args) {
		CustomQueue q = new CustomQueue();
	        q.enqueue(10);
	        q.enqueue(20);
	        q.dequeue();
	        q.dequeue();
	        q.enqueue(30);
	        q.enqueue(40);
	        q.enqueue(50);
//	        q.dequeue();
	        System.out.println("Queue Front : " + q.front.data);
	        System.out.println("Queue Rear : " + q.rear.data);
	}

}
