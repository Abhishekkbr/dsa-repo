package com.dynamic;

public class ClimibingStairs {
	public static void main(String[] args) {
		int waysRecursive = climbingStairsRecursive(4);
		System.out.println(waysRecursive);
		int waysDp = climbingStairsDp(4);
		System.out.println(waysDp);
	}

	private static int climbingStairsDp(int i) {
		// TODO Auto-generated method stub
		if(i <= 1) {
			return 1;
		}
		int[] dp = new int[i+1];
		dp[0] = 1;
		dp[1] = 1;
		 
		for(int n = 2 ; n <= i ; n++) {
			dp[n] = dp[n-1] + dp[n-2];
		}
		
		return dp[i];
	}

	private static int climbingStairsRecursive(int i) {
		// TODO Auto-generated method stub
		if(i == 0 || i == 1)
			return 1;
		return climbingStairsRecursive(i-1)+climbingStairsRecursive(i-2);
	}
}
