package com.dynamic;

public class CombinationSumIV {
	public static void main(String[] args) {
	   int[] nums = {1, 2, 3};
	   int target = 4;
	   System.out.println("Number of combinations: " + combinationSum4(nums, target));
	}

	/**
	 * [1,1,1,1],[1,1,2],[1,2,1],[2,1,1],[2,2],[1,3],[3,1]
	 * @param nums
	 * @param target
	 * @return
	 */

	private static int combinationSum4(int[] nums, int target) {
		// TODO Auto-generated method stub
		int[] dp = new int[target+1];
		dp[0] = 1;
		for(int i = 1; i <= target ; i++) {
			for(int num : nums) {
				if(i >= num) {
					dp[i] += dp[i-num];
				}
			}
		}
		return dp[target];
	}

}
