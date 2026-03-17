import java.util.HashSet;
import java.util.Set;

public class OverridingConcepts {

	public static void main(String[] args) {
		//int a = (int) add(1.0,2.0);
		String s = "abc";
		Set<Integer> ss = new HashSet<>();
//		ss.add(1);
//		ss.add(4);
//		ss.add(3);
//		ss.add(7);
//		ss.add(7);
		//String sa = null;
		add(1, 2);
//		System.out.println(ss);
//		System.out.println(s.substring(0,2));
//		int c = 2,b = 3;
//		double a = (double)(c+b)/2;
//		System.out.println(a);
	}

	private static void add(long j, long i) {
		// TODO Auto-generated method stub
		System.out.println("int method is called");
		//return (int) (i+j);
	}
	
	private static void add(long i, int k) {
		// TODO Auto-generated method stub
		System.out.println("long method is called");
		//return (int) (i+j);
	}
	
	
	
}

