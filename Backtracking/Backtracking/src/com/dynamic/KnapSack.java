package com.dynamic;

public class KnapSack {
    public static void main(String[] args) {
        int[] wt = {1, 3, 4};
        int[] val = {15, 20, 30};
        int W = 4;
        System.out.println(knapSack(wt, val, W));
    }

    private static int knapSack(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n+1][W+1];

        for (int i = 1 ; i <= n ; i++) {
            for (int w = 0 ; w <= W ; w++) {
                dp[i][w] = dp[i-1][w];
                if (wt[i-1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i][w],
                            val[i-1] + dp[i-1] [w-wt[i-1]]
                    );
                }
            }
        }
        return dp[n][W];
    }
}
