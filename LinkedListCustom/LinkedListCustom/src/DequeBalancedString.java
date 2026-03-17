import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class DequeBalancedString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string: ");
		String s = sc.next();
		boolean result = isBalanced(s);	
		System.out.println("Deq result :  "+result);
		boolean stackResult = isBalancedStack(s);
		System.out.println("Stack Result :  "+stackResult);

	}

	private static boolean isBalanced(String s) {
		// TODO Auto-generated method stub
		ArrayDeque<Character> deq = new ArrayDeque<>();
		final char[] input = s.toCharArray();

		for(char c : input) {
			char mc = '.';
			switch(c) {
			case '(':
			case '{':
			case '[':
				deq.addFirst(c);
				break;

			case ')':
				mc = '(';
			case '}':
				if (mc == '.') mc = '{';
			case ']':
				if (mc == '.') mc = '[';

				if (deq.isEmpty () || 
						deq.removeFirst () != mc) {
					return false;
				}
				break;

			default:
				// Any other character is bad input data
				return false;
			}
		}

		return deq.isEmpty ();
	}
	
	
	private static boolean isBalancedStack(String s) {
		Stack<Character> st = new Stack<>();
		for(int i = 0 ; i < s.length() ; i++) {
			char c = s.charAt(i);
			if(c == '(' || c == '[' || c == '{')
				st.push(c);
			else {
				if(!st.isEmpty()) {
					char ch = st.pop();
					if(ch == '(' && c != ')' )
						return false;
					if(ch == '{' && c != '}')
						return false;
					if(ch == '[' && c != ']')
						return false;
				}
				else {
					return false;
				}
			}
		} return st.isEmpty();
	}
}
