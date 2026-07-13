package com.dynamic;

import java.util.Arrays;

public class KnightDialer {
	public static void main(String[] args) {
        int n = 3;
        System.out.println("Total combinations: " + knightDialer(n));
    }

	private static Integer knightDialer(int n) {
		// TODO Auto-generated method stub
		int[][] moves = {
	            {4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9},
	            {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}
	        };
		long[] dp = new long[10];
		Arrays.fill(dp, 1);
		for(int hop = 2 ; hop <= n ; hop++) {
			long[] next = new long[10];
			for(int digit = 0 ; digit <= 9 ; digit++) {
				for(int move: moves[digit]) {
					next[digit] = next[digit] + dp[move];
				}
			}
			dp = next;
		}
		long sum = 0;
		for(long i : dp)  {
			sum += i;
		}
		
		return (int)sum;
	}
}
