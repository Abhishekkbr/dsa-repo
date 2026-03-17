package com.dynamic;

import java.util.Arrays;

public class CoinChange {
	public static void main(String[] args) {
		int[] coins = {1, 2, 3, 8};
		int target = 11;
		int minCoin = minimumCoin(coins, target); // 3+8
		System.out.println(minCoin);
		int[] nums = {1,2,3};
		int targetAmount = 4;  // 1,1,1,1  2+2  1+1+2  1+3
		int coinCombination = coinChange(nums, targetAmount);
		System.out.println(coinCombination);
	}


	//"What changes if order matters?" (i.e. permutations)
	//
	//Then you say:
	//
	//You need to reverse the loops: loop amount first, then coins — like in combinationSum4.
	private static int coinChange(int[] coins, int target) {
		// TODO Auto-generated method stub
		int[] dp = new int[target+1];
		dp[0] = 1;     // 1 way to make amount 0;
		for(int coin : coins) {
			for(int i = coin ; i <=target; i++) {
				dp[i] += dp[i-coin];
			}
		}
		return dp[target];
	}

	private static int minimumCoin(int[] coins, int target) {
		// TODO Auto-generated method stub		
		int[] temp = new int[target+1];
		Arrays.fill(temp, target+1);
		temp[0] = 0;        // to make amount 0 you need 0 coins
		for(int coin : coins) {
			for(int i = coin ; i <= target ; i++) {
				temp[i] = Math.min(temp[i], temp[i-coin]+1);
			}
		}
		return temp[target] == target+1 ? -1 : temp[target];
	}
}
