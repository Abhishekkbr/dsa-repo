import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TwoSumLeet {
	public static List<List<Integer>> threeSum(int[] arr, int target){
		Arrays.sort(arr);
		List<List<Integer>> ans = new ArrayList<>();
		for(int i = 0 ; i < arr.length ; i++) {
			if(i > 0 && arr[i-1] == arr[i]) continue;
			int l = i+1;
			int r = arr.length-1;
			int toFind = target - arr[i];
			while(l < r) {
				if(arr[l] + arr[r] == toFind)
				{
					ans.add(Arrays.asList(arr[i], arr[l], arr[r]));
					while(l < r && arr[l] == arr[l+1]) l++;
					while(l < r && arr[r-1] == arr[r]) r--;
					l++;
					r--;
				}
				else if(arr[l] + arr[r] < toFind)
				{
					l++;
				}else {
					r--;
				}
			}			
		} return ans;
	}
	
	public static int[] twoSum(int[] arr, int target) {
		int[] res = new int[2];
		Map<Integer, Integer> mp = new HashMap<>();
		for(int i = 0 ; i < arr.length ; i++) {
			if(mp.containsKey(arr[i])) {
				res[0] = mp.get(arr[i]);
				res[1] = i;
			}
			mp.put(target - arr[i], i);
		}
		return res;
	}
	
	public static void main(String[] args) {
		int arr[] = {1, 3, 8, -1, -1, 2};
		System.out.println("Running");
		List<List<Integer>> ans = threeSum(arr, 6);
		System.out.println(ans);
		int[] two = {2,7,11,15};
		int[] ansTwo = twoSum(two, 9);
		for(int s : ansTwo) {
			System.out.print(s+ ", ");
		}
	}
}
