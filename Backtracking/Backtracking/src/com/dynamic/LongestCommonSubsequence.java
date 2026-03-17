package com.dynamic;



// time complexity O(m*n) space complexity O (m*n) we can reduce space complexity by defining 1-d array
public class LongestCommonSubsequence {
	public static void main(String[] args) {
		String text1 = "abcde";
	    String text2 = "abfcge";
	    System.out.println("LCS length: " + longestCommonSubsequence(text1, text2));
	}

	private static int longestCommonSubsequence(String text1, String text2) {		
		int m = text1.length();
		int n = text2.length();
		
		int[][] dp = new int[m+1][n+1];
		for(int i = 1 ; i <= m ; i++) {
			for(int j = 1 ; j <= n ; j++) {
				if(text1.charAt(i-1) == text2.charAt(j-1)) {
					dp[i][j] = 1+dp[i-1][j-1];
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp[m][n];
	}

}
