package com.dynamic;

public class KnapSack {
    public static void main(String[] args) {
        int[] wt = {1, 3, 4};
        int[] val = {15, 20, 30};
        int W = 4;
        System.out.println(knapSack(wt, val, W));
        System.out.println(bruteForceKnapSack(wt, val, W));
    }

    private static int bruteForceKnapSack(int[] wt, int[] val, int w) {
        return bruteForceKnapSackRecursion(wt, val, w, 0);
    }

    private static int bruteForceKnapSackRecursion(int[] wt, int[] val, int w, int index) {
        if (index == wt.length || w == 0) {
            return 0;
        }

        if (wt[index] > w)  {
            return bruteForceKnapSackRecursion(wt, val, w, index+1);
        }

        int take = val[index] + bruteForceKnapSackRecursion(wt, val, w - wt[index], index+1);

        int skip = bruteForceKnapSackRecursion(wt, val, w, index+1);

        return Math.max(take, skip);
    }

    private static int knapSack(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n+1][W+1];

        for (int i = 1 ; i <= n ; i++) {
            for (int w = 0 ; w <= W ; w++) {
                dp[i][w] = dp[i-1][w];    //Maximum value we can achieve using first i items with capacity w
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
