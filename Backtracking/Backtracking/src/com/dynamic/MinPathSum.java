package com.dynamic;


// Time and Space complexity = O(m * n) 

public class MinPathSum {
	public static void main(String[] args) {
		int[][] grid = {
	            {1, 3, 1, 2},
	            {1, 1, 2, 1},
	            {4, 1, 1, 1}
	        };
	        int result = minPathSum(grid);
	        System.out.println("Minimum Path Sum: " + result); 
	}

	private static int minPathSum(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = grid[0][0];

		// fill 1st row
		for (int i = 1; i < col; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}

		// fill 1st column
		for (int j = 1; j < row; j++) {
			dp[j][0] = dp[j - 1][0] + grid[j][0];
		}

		// fill rest
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[row - 1][col - 1];
	}
}
