class QueueCustom{
	private int[] arr;
	private int front;
	private int rear;
	private int capacity;
	private int count;
	
	QueueCustom(int size){
		arr = new int[size];
		capacity = size;
		front = 0;
		rear = -1;
		count = 0;
	}
	
	public void dequeue() {
		if(isEmpty()) {
			System.out.println("Underflow program terminated");
			System.exit(1);
		}
		System.out.println("Removing  "+arr[front]);
		front = (front + 1) % capacity;
		count--;
	}
	
	public void enqueue(int item) {
		if(isFull()) {
			System.out.println("Overflow program terminated");
			System.exit(1);
		}
		System.out.println("Inserting " + item);
		 
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        count++;
	}

	public boolean isFull() {
		// TODO Auto-generated method stub
		return (size()==capacity);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size()==0);
	}
	
	public int peek() {
		if (isEmpty())
        {
            System.out.println("Underflow\nProgram Terminated");
            System.exit(1);
        }
        return arr[front];
	}

	public int size() {
		// TODO Auto-generated method stub
		return count;
	}
	
}
public class QueueImplementation {
	public static void main(String[] args) {
		QueueCustom q = new QueueCustom(5);
		 
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
 
        System.out.println("The front element is " + q.peek());
        q.dequeue();
        System.out.println("The front element is " + q.peek());
 
        System.out.println("The queue size is " + q.size());
 
        q.dequeue();
        q.dequeue();
 
        if (q.isEmpty()) {
            System.out.println("The queue is empty");
        }
        else {
            System.out.println("The queue is not empty");
        }
	}
}
