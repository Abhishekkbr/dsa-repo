import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwoPointers {
	public static void main(String[] args) {
		int[] nums = {-2, 2, 8, 12, 15};
		int target = 10;
		List<List<Integer>> ans = twoSum(nums, target);
		System.out.println(ans);
		List<List<Integer>> ansMap = twoSumMap(nums, target);
		System.out.println(ansMap);
	}

	private static List<List<Integer>> twoSumMap(int[] nums, int target) {
		// TODO Auto-generated method stub
		List<List<Integer>> ans = new ArrayList<>();
//		Map<Integer, Integer> hp = new HashMap<>();
//		for(int i = 0 ; i < nums.length ; i++) {
//			int complement = target - nums[i];
//			if(hp.containsKey(complement)) {
//				ans.add(Arrays.asList(nums[i], complement));
//			}
//			hp.put(nums[i], i);
//		}
		
		Set<Integer> hp = new HashSet<>();
		for(int i = 0 ; i < nums.length ; i++) {
			int complement = target - nums[i];
			if(hp.contains(complement)) {
				ans.add(Arrays.asList(nums[i], complement));
			}
			hp.add(nums[i]);
		}
		return ans;
	}

	private static List<List<Integer>> twoSum(int[] nums, int target) {
		// TODO Auto-generated method stub
		int left = 0;
		int right = nums.length-1;
		List<List<Integer>> ans = new ArrayList<>();
		while(left < right) {
			if(nums[left] + nums[right] == target) {
				ans.add(Arrays.asList(nums[left], nums[right]));
				left++;
				right--;
			}
			else if(nums[left] + nums[right] < target){
				left++;
			}
			else {
				right--;
			}
		}
		return ans;
	}
}
