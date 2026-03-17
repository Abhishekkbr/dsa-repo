import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
	public static void main(String[] args) {
		int[] arr = {13,7,12,14};
		int[] ng = new int[arr.length];
		Stack<Integer> st = new Stack<>();
//		st.push(arr[arr.length-1]);
//		ng[arr.length-1] = -1;
//		for(int i = arr.length-2 ; i >= 0 ; i--) {
//			while(!st.isEmpty() && st.peek() <= arr[i]) {
//				st.pop();
//			} if (st.isEmpty()) {
//				ng[i] = -1;
//			} else {
//				ng[i] = st.peek();
//			}
//			st.push(arr[i]);
//		}
//		Arrays.stream(ng).forEach(System.out::println);
		st.push(arr[arr.length-1]);
		ng[arr.length-1] = -1;
		for(int i = arr.length-2 ; i >= 0 ; i--) {
			while(!st.isEmpty() && st.peek() <= arr[i]) {
				st.pop();
			} if(st.isEmpty()) {
				ng[i] = -1;
			} else {
				ng[i] = st.peek();
			}
			st.push(arr[i]);
		}
		Arrays.stream(ng).forEach(System.out::println);
	}
}
