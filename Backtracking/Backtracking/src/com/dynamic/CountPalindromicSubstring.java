package com.dynamic;

public class CountPalindromicSubstring {

	public static void main(String[] args) {
		// a,a,a,b,aa,aa,aaa
		System.out.println(countPalindromicSubstrings("aaab")); // Output: 7
	}

	private static int countPalindromicSubstrings(String string) {
		int n = string.length();
		boolean[][] dp = new boolean[n][n];
		int count = 0;

		for(int end = 0 ; end < n ; end++) {
			for(int start = 0 ; start <= end ; start++) {
				if(string.charAt(start) == string.charAt(end)) {
					if((end-start) <=2 || dp[start+1][end-1]) {			// <=2 (aba, cbc) , [start+1][end-1] (abcba) - will check bcb
						dp[start][end] = true;
						count++;
					}
				}
			}
		}
		return count;
	}

	private static int countPalindromicSubstrings1(String string) {
		int n = string.length();
		boolean[][] dp = new boolean[n][n];
		int count = 0;

		for(int end = 0 ; end < n ; end++) {
            for(int start = 0 ; start <= end ; start++) {
                if(string.charAt(end) == string.charAt(start)) {
                    if((end - start) <= 2 || dp[start+1][end-1]){
                        dp[start][end] = true;
                        count++;
                    }
                }
            }
        }
		return count;
	}
}
