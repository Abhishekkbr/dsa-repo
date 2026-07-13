
public class StackCustom {
	
	private int[] values;
	private int capacity;
	private int top;
	
	StackCustom(int size){
		values = new int[size];
		capacity = size;
		top = -1;
	}
	
	public void push(int x) {
		if(isFull()) {
			System.out.println("Stack is already full");
			System.exit(1);
		}
		System.out.println("Inserted element: "+x);
		values[++top] = x;
	}
	
	private boolean isFull() {
		// TODO Auto-generated method stub
		
		return top == capacity - 1;
	}

	public int pop() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
			System.exit(1);
		}
		System.out.println("deleted element: "+peek());
		return values[top--];
	}

	private int peek() {
		// TODO Auto-generated method stub
		if(!isEmpty()) {
			return values[top] ;
		}
		else {
			System.exit(1);
		}
		return -1;
	}

	private boolean isEmpty() {
		// TODO Auto-generated method stub
		return top == -1;
	}
	
	public static void main(String[] args) {
		StackCustom stack = new StackCustom(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		
		stack.pop();
		stack.pop();
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
	}
	

}
