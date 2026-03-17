import java.util.*;
import java.util.stream.Collectors;

public class TwoSum {
	public static void main(String[] args) {
		String a = "abhishek";
		System.out.println(a);
		System.out.println(String.valueOf(a));
		
		
		List<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(4);
		arr.add(3);
		arr.add(5);
		arr.add(2);
		
		List<String> arrString = new ArrayList<>();
		arrString.add("c");
		arrString.add("b");
		arrString.add("d");
		arrString.add("a");
		arrString.add("f");
		
		arr.forEach(s -> {if(s%2 == 0) System.out.print(s+ " ");});
		List<Integer> m =  arr.stream().map(s -> s*s).collect(Collectors.toList());
		System.out.println(m);
		List<Integer> f = arr.stream().filter(s -> s%2==0).collect(Collectors.toList());
		System.out.println(f);
		
		List<String> sorted = arrString.stream().sorted().collect(Collectors.toList());
		System.out.println(sorted);
		
		Optional<Integer> red = arr.stream().reduce((x,y) -> x+y);//.collect(Collectors.toList());
		System.out.println(red);
		
		List<String> sorted1 = arrString.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(sorted1);
		
		List<Integer> list = Arrays.asList(1, 8, 10, 3, 7, 5, 15);
		int ans = list.stream()
		            .peek(num -> System.out.println("will filter " + num))
		            .filter(x -> x > 5)
		            .findFirst()
		            .get();
		System.out.println(ans);
		
		Map<String, Integer> missions = new TreeMap<>();
		missions.put("one", 1);
		missions.put("two", 2);
		missions.put("three", 3);
		missions.put("four", 4);
		missions.entrySet().forEach(e -> System.out.println(e));
		
		List<String> ls = missions.entrySet().stream().filter(e -> e.getValue()==4).map(Map.Entry :: getKey).collect(Collectors.toList());
				//.map(Map.Entry::getKey).collect(Collectors.toList());
		ls.forEach(e -> System.out.println(e));
		
//		Map<String, Integer> hs = missions.entrySet().stream().map(m -> m.getValue()*2)
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		ls.forEach(e -> System.out.println(e));
	}
}
