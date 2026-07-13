package com.dynamic;

import java.util.Arrays;

public class UniquePaths {
	public static void main(String[] args) {
		int row = 3;
		int col = 3;
		int path = uniquePath(row, col);
		System.out.println(path);
		int optimizedPath = uniqueOptimizedPath(row, col);
		System.out.println(optimizedPath);				
	}

	private static int uniqueOptimizedPath(int row, int col) {
		// TODO Auto-generated method stub
		int[] dp = new int[col];
		Arrays.fill(dp, 1);
		for(int i = 1 ; i < row ; i++) {
			for(int j = 1 ; j < col ; j++) {
				dp[j] += dp[j-1];
			}
		}
		
		return dp[col-1];
	}

	//time and space complexity = O(row × col)
	private static int uniquePath(int row, int col) {
		int[][] temp = new int[row][col];
		for(int i =  0 ; i < row ; i++) temp[i][0] = 1;
		for(int j = 0  ; j < col ; j++) temp[0][j] = 1;
		for(int i = 1 ; i < row ; i++) {
			for(int j = 1 ; j < col ; j++) {
				temp[i][j] = temp[i-1][j] + temp[i][j-1];
			}
		}
		return temp[row-1][col-1];
	}
}
