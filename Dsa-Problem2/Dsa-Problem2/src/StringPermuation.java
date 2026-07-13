import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StringPermuation {
	
	static class A{
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string :");
		String s = sc.next();
		permuation(s);
		//System.out.println("Permutation result -> "+s);
//		Collections.sort(ss);
		System.out.println(ss);
		List<String> list = new ArrayList<>();

		list.add("Hello");
		list.add("Hello");
		list.add("World");
		
//		 Map<String, Long> counted = list.stream()
//		            .collect(Collectors.groupingBy(m -> m, Collectors.counting()));
		
		Map<String, Long> count = list.stream().collect(Collectors.groupingBy(m->m, Collectors.counting())); 

		        System.out.println(count);
		        
		 A a = new A();
		 A a1 = a;
		 System.out.println(a.hashCode() +"--------->"+a1.hashCode());
	}
	static List<String> ss = new ArrayList<>();
	private static void permuation(String s) {
		// TODO Auto-generated method stub
//		permuation("",s);
		permutation("", s);
		
	}
	
	private static void permutation(String perm, String word) {
		// TODO Auto-generated method stub
		if(word.isEmpty()) {
			ss.add(perm);
			System.out.println("Ans :"+perm + word);
		}else {
			for(int i = 0 ; i < word.length() ; i++) {
				permutation(perm+word.charAt(i), word.substring(0, i) + word.substring(i+1, word.length()));
			}
		}
	}

	private static void permuation(String perm, String word) {
		// TODO Auto-generated method stub
		if(word.isEmpty()) {
			ss.add(perm);
			System.out.println("Ans  :"+perm + word);
		}else {
			for(int i=0 ; i<word.length() ; i++) {
				permuation(perm+word.charAt(i), word.substring(0, i)
						+ word.substring(i+1, word.length()));
			}
		}		
	}

}
