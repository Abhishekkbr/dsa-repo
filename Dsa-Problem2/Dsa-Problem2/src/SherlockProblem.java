import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SherlockProblem {
	public static void main(String[] args) {
		String result = isValid("aaaaabbcc");
		System.out.println(result);
	}

	public static String isValid(String s) {
		// Write your code here
		HashMap<Character, Integer> map = new HashMap<>();
		HashMap<Integer, Integer> countFreq = new HashMap<>();
		char[] tmp = s.toCharArray();
		int min = 0;
		int max = 0;
		int minimum = 0;
		int maximum = 0;
		int minKey = 0;
		int maxKey = 0;
		int lastmin = 0;
		int lastmax = 0;
		for(char i : tmp){
			if(map.containsKey(i)){
				map.put(i, map.get(i)+1);
			}else{
				map.put(i, 1);
			}
		}

		for(Integer i : map.values()){
			if(countFreq.containsKey(i)){
				countFreq.put(i, countFreq.get(i)+1);
			}else{
				countFreq.put(i, 1);
			}
		}

		for(Map.Entry<Integer, Integer> pair : countFreq.entrySet()){
			System.out.println(pair.getKey()  +"   "+ pair.getValue());
		}

		if(countFreq.size() > 2){
			return "NO";
		}
		if(countFreq.size() == 1){
			return "YES";
		}


		min = countFreq.get(countFreq.keySet().toArray()[0]);
		max = countFreq.get(countFreq.keySet().toArray()[1]);


		int[] arr = new int[2];
		arr[0] = min;
		arr[1] = max;
		Arrays.sort(arr);

		minimum = arr[0];
		maximum = arr[1];

		System.out.println("min : "+minimum);
		System.out.println("max : "+maximum);
		minKey = (int)countFreq.keySet().toArray()[0];
		maxKey = (int)countFreq.keySet().toArray()[1];
		System.out.println("minKey :"+minKey);
		System.out.println("maxkey :"+maxKey);
		int[] arrKey = new int[2];
		arrKey[0] = minKey;
		arrKey[1] = maxKey;
		Arrays.sort(arrKey);
		lastmin = arrKey[0];
		lastmax = arrKey[1];
		System.out.println("lastMin :"+lastmin);
		System.out.println("lastMax :"+lastmax);


		for(int key: countFreq.keySet()) {
			if(countFreq.get(key) == maximum) {
				if(key == 1) {
					return "NO";
				}
			}
			if(countFreq.get(key) == minimum) {
				if(key > 1) {
					return "NO";
				}
			}
		}

		if(minimum != 1){
			return "NO";
		}

		if(minimum == 1){
			return "YES";
		}



		return "YES";

	}
}