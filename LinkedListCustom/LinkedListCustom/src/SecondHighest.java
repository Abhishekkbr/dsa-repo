import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SecondHighest {
	public static void main(String[] args) {
		Integer[] input = {34, 12, 45, 20, 8};
		List<Integer> al = Arrays.asList(input);
		System.out.println(secondLargest(input));
		int[] inp = {34, 12, 45, 20, 8};
		System.out.println(Arrays.stream(inp).max());
		System.out.println(al.stream().max(Integer::compare));
		System.out.println(al.stream().max(Integer::compare));
		System.out.println(al.stream().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst());
	}

	private static int secondLargest(Integer[] input) {
		// TODO Auto-generated method stub
		int highest = 0;
		int secondHighest = 0;
		
//		for(int num : input) {
//			if(num > highest) {
//				secondHighest = highest;
//				highest = num;
//			}else if (num > secondHighest) {
//				secondHighest = num;
//			}
//		}
		for(int i : input) {
			if(i > highest) {
				secondHighest = highest;
				highest = i;
			}else if(i > secondHighest) {
				secondHighest = i;
			}
		}
		return secondHighest; 
	}
}
