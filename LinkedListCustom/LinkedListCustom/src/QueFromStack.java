import java.util.Stack;

public class QueFromStack {

	static class Queque {
		Stack<Integer> stack1;
		Stack<Integer> stack2;
	}
	
	static void push(Stack<Integer> s1, int data) {
		s1.push(data);
	}
	
	static int pop(Stack<Integer> s1) {
		if(s1.isEmpty()) {
			System.out.println("Stack is underflow");
			System.exit(1);
		}
		return s1.pop();
	}
	
	static void enqueue(Queque q, int data) {
		push(q.stack1, data);
	}
	
	static int dequqe(Queque q) {
		int x;
		if(q.stack1.isEmpty() && q.stack2.isEmpty()) {
			System.out.println("Queque is empty");
			System.exit(1);
		}
		if(q.stack2.isEmpty()) {
			while(!q.stack1.isEmpty()) {
				x = pop(q.stack1);
				push(q.stack2,x);
			}
		}
		x = pop(q.stack2);
		
		return x;
	}
	 
	public static void main(String[] args) {
		Queque q = new Queque();
		q.stack1 = new Stack<Integer>();
		q.stack2 = new Stack<Integer>();
		enqueue(q, 1);
		enqueue(q, 2);
		enqueue(q, 3);
		
		System.out.print(dequqe(q) + " ");
		System.out.println(dequqe(q) + " ");
		//System.out.println(dequqe(q) + " ");
	}
}
