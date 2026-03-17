import java.util.ArrayList;

public class SubsetRecursion {

	public static void main(String[] args) {
		//int[] arr = {1,2,3,4};
		//String 
		//subset("abc", "");
//		ArrayList<String> sb = subsetArray("abc", "");
//		sb.forEach(System.out::println);
		
		//permute("abc", "");
		
		String s = "abc";
		int i = s.charAt(1) - '0';
		System.out.println(i);
		//System.out.println("done");
	}

	private static void permute(String up, String p) {
		// TODO Auto-generated method stub
		if(up.isEmpty()) {
			System.out.println(p);
			return;
		}
		char ch = up.charAt(0);
		for(int i = 0 ; i <= p.length() ; i++) {
			String f = p.substring(0,i);
			String l = p.substring(i, p.length());
			permute(up.substring(1), f+ch+l);
		}
	}

	private static ArrayList<String> subsetArray(String un, String p) {
		// TODO Auto-generated method stub
		if(un.isEmpty()) {
			return new ArrayList<String>() {{ add(p); }};
		}
		
		char ch = un.charAt(0);
		ArrayList<String> left = subsetArray(un.substring(1), p+ch);
		ArrayList<String> right = subsetArray(un.substring(1), p);
		left.addAll(right);
		return left;
	}

	private static void subset(String un, String p) {
		// TODO Auto-generated method stub
		if(un.isEmpty()) {
			System.out.println(p);
			return;
		}
		
		char ch = un.charAt(0);
		subset(un.substring(1), p+ch);
		subset(un.substring(1), p);
	}
}
