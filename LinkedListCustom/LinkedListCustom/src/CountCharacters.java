import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountCharacters {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string");
		String temp = sc.next();
//		int[] count = new int[256];
//		
//		for(int i=0; i<temp.length(); i++) {
//			count[temp.charAt(i)]++;
//		}
//		int max = -1;
//		char result = ' ';
//		for(int i=0; i<count.length ;i++) {
//			System.out.println(count[i]);
//		}
		
		// 2nd Method
		int[] count = new int[temp.length()];
		char[] ch = temp.toCharArray();
		for(int i=0 ; i<count.length;i++) {
			count[i] = 1;
			for(int j = i+1; j<count.length ; j++) {
				if(ch[i] == ch[j]) {
					count[i]++;
					ch[j] = '0';
				}  
			}
		}
		
		for (int i = 0; i < ch.length; i++) {
			if(ch[i] != '0') {
			System.out.println(ch[i] +"-------"+ count[i]);
			}
			
		}
		
//		Map<Character, Long> mp = temp.chars().mapToObj(m -> (char) m)
//				.collect(Collectors.groupingBy(m -> m, Collectors.counting()));

		Map<Character, Long> mp = temp.chars().mapToObj(m -> (char) m)
				.collect(Collectors.groupingBy(m -> m , Collectors.counting()));
		
		mp.forEach((k,v) -> System.out.println(k + "--------->" + v));
	}
}
