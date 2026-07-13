package com.dynamic;


//time - O(n × subset)  space - O(subset)
public class TargetSum {
	public static void main(String[] args) {
		int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("Number of ways: " + findTargetSumWays(nums, target));
	}

	private static int findTargetSumWays(int[] nums, int target) {
		// TODO Auto-generated method stub
		int sum = 0;
		for(int num : nums) {
			sum += num;
		}
		if((sum+target)%2 != 0 || sum < Math.abs(target)) {
			return 0;
		}
		
		int subset = (sum + target)/2;
		return countSubsets(nums, subset);
	}

	private static int countSubsets(int[] nums, int subset) {
		// TODO Auto-generated method stub
		int[] dp = new int[subset+1];
		dp[0] = 1;
		for(int num : nums) {
			for(int i = subset ; i >= num ; i--) {
				dp[i] += dp[i-num];
			}
		}
		return dp[subset];
	}
}
