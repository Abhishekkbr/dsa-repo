import java.util.Stack;

public class StackSort {
	public static void main(String[] args) {
		Stack<Integer> input = new Stack<Integer>();
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);
      
        // This is the temporary stack
        Stack<Integer> tmpStack=sortstack(input);
        System.out.println("Sorted numbers are:");
      
        while (!tmpStack.empty())
        {
            System.out.print(tmpStack.pop()+" ");
        } 
	}

	private static Stack<Integer> sortstack(Stack<Integer> input) {
		// TODO Auto-generated method stub
		Stack<Integer> tmpstack = new Stack<>();
		while(!input.empty()) {
			int tmp = input.pop();
			while(!tmpstack.isEmpty() && tmpstack.peek() > tmp) {
				input.push(tmpstack.pop());
			}
			tmpstack.push(tmp);
		}
		return tmpstack;
	}
}
