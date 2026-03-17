import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

	public static void main(String[] args) {
		int n = 6;
		List<String> answer = generateParenthesis(3);
		System.out.println(answer);
	}

    private static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        parenGen("(", 1, 0, ans, n);
        return ans;
    }

    private static void parenGen(String s, int open, int close, List<String> ans, int n) {
        if(s.length() == n*2) {
            ans.add(s);
        }

        if(open < n) {
            parenGen(s+"(", open+1, close, ans, n);
        }

        if(close < open) {
            parenGen(s+")", open, close+1, ans, n);
        }
    }


//	public static List<String> generateParenthesis(int n) {
//        List<String> result = new ArrayList<>();
//        parenGen("(", 1, 0, result, n);
//        return result;
//    }
//
//    public static void parenGen(String s, int open, int close, List<String> result, int n){
//        if(s.length() == 2*n){
//            result.add(s);
//            return;
//        }
//
//
//        if(open < n)
//            parenGen(s+"(", open+1, close, result, n);
//
//        if(close < open)
//            parenGen(s+")", open, close+1, result, n);
//    }
}
