package com.dynamic;

import java.util.Arrays;
import java.util.List;

//Time -> O(n × total), Space ->  O(total)

public class ParitionSum {
	public static void main(String[] args) {
        int[] nums1 = {1, 3, 2, 2}; // true
        Integer[] nums2 = {1, 3, 2, 2};  // false
        System.out.println(canPartition(nums1));
        //System.out.println(canPartition(nums2));
        System.out.println(checkPartition(nums2));
    }

	private static boolean canPartition(int[] nums) {
		// TODO Auto-generated method stub
		int total = 0;
		for(int n : nums) {
			total += n;
		}
		if(total %2 != 0) return false;
		int target = total/2;
		boolean[] dp = new boolean[target+1];
		dp[0] = true;
		for(int i : nums) {
			for(int n = target ; n >= i ; n--) {
				dp[n] = dp[n] || dp[n-i];
			}
		}
		return dp[target];
	}
	
	private static boolean checkPartition(Integer[] nums) {
		List<Integer> ll = Arrays.asList(nums);
		ll.stream().sorted();
		int target = ll.get(ll.size()-1);
		for(Integer num  : nums) {
			if(num != ll.get(ll.size()-1)) {
				target = target - num; 
			}
		}
		
		return target == 0 ?  true : false;
	}
}
