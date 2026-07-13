package com.arrays;

public class StockBuySell {
    public static void main(String[] args) {
        int[] input = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(input));
    }

    private static int maxProfit(int[] input) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i : input) {
            if(i < minPrice) {
                minPrice = i;
            } else if (i - minPrice > maxProfit) {
                maxProfit = i - minPrice;
            }
        }
        return maxProfit;
    }
}
