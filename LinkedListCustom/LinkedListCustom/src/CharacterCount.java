import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterCount {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string: ");
		String s = sc.next();
		char[] input = s.toCharArray();
		HashMap<Character, Integer> dict = new HashMap<>();
		for(char i : input) {
			dict.put(i, dict.getOrDefault(i, 0) + 1);
		}
		System.out.println(dict);

//		Stream<Character> converted = s.chars().mapToObj(m -> (char) m);
//		converted.forEach(m ->  System.out.println(m));
//		Map<Character, Long> characterCountMap = s.chars().mapToObj(m -> (char) m)
//				.collect(Collectors.groupingBy(m->m, Collectors.counting()));
		Map<Character, Long> characterCountMap = s.chars().mapToObj(m -> (char) m)
				.collect(Collectors.groupingBy(m -> m, Collectors.counting()));
		List<Character> charList = characterCountMap.entrySet().stream()
				.filter(m -> m.getValue() > 1).map(Map.Entry :: getKey).collect(Collectors.toList());
		System.out.println(characterCountMap);
		System.out.println(charList);
		charList.forEach(System.out::println);
		// characterStream
		// .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
	}

}
