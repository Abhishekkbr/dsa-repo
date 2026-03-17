import java.util.Stack;

public class StackExpression {
	public static void main(String[] args) {
		String exp="231*+9-";
        System.out.println("postfix evaluation: "+evaluatePostfix(exp));
        String exprsn = "+9*26";
        System.out.println("prefix evaluation: "+evaluatePrefix(exprsn));
	}

	private static int evaluatePrefix(String exprsn) {
		// TODO Auto-generated method stub
		Stack<Integer> stpre = new Stack<>();
		int val1 = 0;
		int val2 = 0;
		for(int i = exprsn.length()-1 ; i>=0 ; i--) {
			char c = exprsn.charAt(i);
			if(Character.isDigit(c)) {
				stpre.push(Integer.parseInt(String.valueOf(c)));
			}else {
				val1 = stpre.pop();
				val2 = stpre.pop();
				switch(c) {
					case '+':
						stpre.push(val2 + val1);
						break;
					case '*':
						stpre.push(val2 * val1);
						break;
					case '-':
						stpre.push(val2 - val1);
						break;
					case '/':
						stpre.push(val2 / val1);
						break;
				}
			}
		}
		return stpre.pop();
	}

	private static int evaluatePostfix(String exp) {
		// TODO Auto-generated method stub
		Stack<Integer> st = new Stack<>();
		int first = 0;
		int sec = 0;
		for(int i=0 ; i<exp.length() ; i++) {
			char c = exp.charAt(i);
			if(Character.isDigit(c)) {
				st.push(Integer.parseInt(String.valueOf(c)));
			}else {
				first = st.pop();
				sec = st.pop();
				switch(c) {
					case '+':
						st.push(sec + first);
						break;
					case '-':
						st.push(sec - first);
						break;
					case '*':
						st.push(sec * first);
						break;
					case '/':
						st.push(sec / first);
						break;
				}
			}
		}
		return st.pop();
	}

}
