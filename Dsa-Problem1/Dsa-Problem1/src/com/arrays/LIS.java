package com.arrays;

import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        int result = findLongest(arr);
        System.out.println(result);
    }

    private static int findLongest(int[] arr) {
        int[] dp = new int[arr.length];
        int best = 1;
        Arrays.fill(dp, 1);
        for (int i = 1 ; i < arr.length ; i++) {
            for (int j = 0 ; j < i ; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }
}