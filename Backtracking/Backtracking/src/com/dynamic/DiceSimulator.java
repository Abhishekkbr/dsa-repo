package com.dynamic;

public class DiceSimulator {
	public static void main(String[] args) {
        int n = 2;
        int[] rollMax = {1, 1, 2, 2, 2, 3};  // Constraints per face (1 to 6)

        System.out.println("Number of valid sequences: " + dieSimulator(n, rollMax));
        // Output: 34 (expected result for n = 2)
    }

	private static int dieSimulator(int n, int[] rollMax) {
		// TODO Auto-generated method stub
		int[][][] dp = new int[n+1][6][16];
		for(int i = 0 ; i < 6 ; i++) {
			dp[1][i][1] = 1;
		}
		
		for(int roll = 2 ; roll <=n ; roll++) {
			for(int curr = 0 ; curr < 6; curr++) {
				for(int prev = 0 ; prev < 6 ; prev++) {
					for(int count = 1 ; count <= rollMax[prev] ; count++) {
						if(curr == prev) {
							if(count+1 <= rollMax[curr]) {
								dp[roll][curr][count+1] = dp[roll][curr][count+1] + dp[roll-1][curr][count];
							}
						} else {
							dp[roll][curr][1] = dp[roll][curr][1] + dp[roll-1][prev][count];
						}
					}
				}
			}
		}
		
		int total = 0;
		for(int i = 0  ; i < 6 ; i++) {
			for(int count = 0 ; count <= rollMax[i] ; count++) {
				total = total + dp[n][i][count];
			}
		}
		
		
		return total;
	}
}
