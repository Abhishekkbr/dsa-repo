package com.dynamic;

public class RobHouse {
    public static void main (String[] args) {
        int[] houses = {2,7,9,13,1};
        int ans = robHouse(houses);
        System.out.println(ans);
    }

    private static int robHouse(int[] houses) {
        int n = houses.length;
        if (n == 1) {
            return houses[0];
        }
        int[] dp = new int[n];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);
        for(int i = 2 ; i < n ; i++) {
            dp[i] = Math.max(dp[i-2] + houses[i], dp[i-1]);
        }
        return dp[n-1];
    }
}
