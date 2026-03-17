import java.util.Stack;

public class BinaryInteger {

	public static void main(String[] args) {
		int num = 45;
		decimalToBinary(num);
		binaryStack(num);
	}

	private static void decimalToBinary(int num) {
		// TODO Auto-generated method stub
		int[] binary = new int[35];
		int j = 0;
		while(num > 0) {
			binary[j++] = num%2;
			num = num/2;
		}
		for(int i = j-1 ; i>=0 ; i--) {
			System.out.print(binary[i]+"");
		}
		System.out.println();
	}
	
	
	private static void binaryStack(int num) {
		Stack<Integer> st = new Stack<>();
		while(num > 0) {
			st.push(num%2);
			num = num/2;
		}
		while(!st.empty()) {
			System.out.print(st.pop()+"");
		}
	}
}
