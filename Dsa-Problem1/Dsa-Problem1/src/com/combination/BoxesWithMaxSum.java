package com.combination;

import java.util.Arrays;

public class BoxesWithMaxSum {
    public static void main(String[] args) {
        int remaining = 5;
        int max = 5;
        int slots = 2;

        generateCombination(remaining, max, slots, new int[slots], 0);
    }

    private static void generateCombination(int remaining, int max, int slots, int[] slotDetail, int index) {
        if (index == slots) {
            if (remaining == 0) {
                System.out.println(Arrays.toString(slotDetail
                ));
            }
            return;
        }

        for (int i = 0 ; i <= max ; i++) {
            if (i <= remaining) {
                slotDetail[index] = i;
                generateCombination(remaining - i, max, slots, slotDetail, index+1);
            }
        }
    }
}
