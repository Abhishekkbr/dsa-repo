package com.dynamic;

import java.util.Arrays;

public class MinimumCoin {
    public static void main (String[] args) {
        int target = 6;
        int[] coins = {1,3,4};
        int[] memo = new int[target+1];
        Arrays.fill(memo, -1);
        int ans = recursion(target, coins, memo);
        System.out.println(ans);
    }

    private static int recursion(int target, int[] coins, int[] memo) {
        if (target == 0) {
            return 0;
        }

        if (target < 0 ) return Integer.MAX_VALUE;
        if (memo[target] != -1) return memo[target];

        int best = Integer.MAX_VALUE;

        for (int coin : coins) {
            int result = recursion(target-coin, coins, memo);
            if (result != Integer.MAX_VALUE) {
                best = Math.min(best, 1 + result);
            }
        }
        memo[target] = best;
        return best;
    }

}
