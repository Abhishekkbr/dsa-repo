package com.dynamic;

import java.util.List;

public class MinimumCostClimbing {
	public static void main(String[] args) {
		List<Integer> cost = List.of(10,15,20);
		int minCost = minCost(cost);
		System.out.println(minCost);
	}

	private static int minCost(List<Integer> cost) {
		// TODO Auto-generated method stub
		int[] dp = new int[cost.size()];
		dp[0] = cost.get(0);
		dp[1] = cost.get(1);
		for(int i = 2 ; i < cost.size() ; i++) {
			dp[i] = cost.get(i) + Math.min(dp[i-1], dp[i-2]);
		}
		return Math.min(dp[cost.size() - 1], dp[cost.size() - 2]);
	}
}
