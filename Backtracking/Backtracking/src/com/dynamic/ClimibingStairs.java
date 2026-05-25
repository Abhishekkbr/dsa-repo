package com.dynamic;

public class ClimibingStairs {
	public static void main(String[] args) {
		int waysRecursive = climbingStairsRecursive(4);
		System.out.println(waysRecursive);
		int waysDp = climbingStairsDp(4);
		System.out.println(waysDp);
	}

	private static int climbingStairsDp(int n) {
		// TODO Auto-generated method stub
		if (n <= 2) {
            return n;
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3 ; i <= n ; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
	}

	private static int climbingStairsRecursive(int i) {
		// TODO Auto-generated method stub
		if(i == 0 || i == 1)
			return 1;
		return climbingStairsRecursive(i-1)+climbingStairsRecursive(i-2);
	}
}
