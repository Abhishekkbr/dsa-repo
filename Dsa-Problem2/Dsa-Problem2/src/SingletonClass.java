import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SingletonClass {
//	private static SingletonClass sc;
//	
//	private SingletonClass() {
//		
//	}
//	
//	public static SingletonClass getInstance() {
//		if(sc == null) {
//			sc = new SingletonClass();
//		}
//		
//		return sc;
//	}
	
	public static void main(String[] args) {
		String s ="heis$,%4000";
		String a = s.substring(s.indexOf("$")).replaceAll("[$%,]", "");
		System.out.println(a);
		
		Integer[] an  = {1,2,3,4,5,6,7};
		
		List<Integer> al = new ArrayList<Integer>(Arrays.asList(an));
		
		Optional<Integer> ans = al.stream().map(i -> i * i).filter(i -> i>2).reduce((b,c)->b+c);
		System.out.println(al);
		System.out.println(ans);
	}
}
