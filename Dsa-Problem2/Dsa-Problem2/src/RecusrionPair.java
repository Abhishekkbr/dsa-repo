import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecusrionPair {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4);
		findPairs(list, 0, new ArrayList<>(), 3);
	}

	private static List<List<Integer>> findPairs(List<Integer> list, int index, ArrayList arrayList, int jremain) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = new ArrayList<>();
		if(jremain == 0) {
			System.out.println(arrayList);
			result.add(new ArrayList<>(arrayList));
			return result;
		}
		for(int i = index ; i < list.size() ; i++) {
			arrayList.add(list.get(i));
			result.addAll(findPairs(list, i+1, arrayList, jremain-1));
			arrayList.remove(arrayList.size()-1);
		}
		return result;
	}
}
