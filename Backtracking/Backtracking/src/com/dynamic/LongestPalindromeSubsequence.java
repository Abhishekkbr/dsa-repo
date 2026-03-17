package com.dynamic;

public class LongestPalindromeSubsequence {

	public static void main(String[] args) {
		System.out.println(longestPalindromeSubseq("abbac"));
	}

	private static int longestPalindromeSubseq(String string) {
		// TODO Auto-generated method stub
		int n = string.length();
		int[][] dp = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			dp[i][i] = 1;
		}
		
		for(int len = 2 ; len <= n ; len++) {
			for(int i =  0 ; i <= n-len ; i++) {
				int j = i+len-1;    //j also refers to last index of substring this will help to check all palindrome values such as b, bb, bbb, bbba etc
				if(string.charAt(i) == string.charAt(j)) {
					dp[i][j] = (len == 2) ? 2 : dp[i+1][j-1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
				}
			}
		}
		
		return dp[0][n-1];
	}
}
