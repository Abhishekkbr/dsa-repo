package com.arrays;

import java.sql.Array;
import java.util.HashMap;

public class SubArrayWithAevarage {
    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 4, 5};
        double target = 3.0;

        int[] result = findSubarrayWithAverage(arr, target);

        if (result[0] != -1) {
            System.out.println("Found subarray from index " + result[0] + " to " + result[1]);
            System.out.print("Elements: ");
            for (int i = result[0]; i <= result[1]; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            System.out.println("No subarray found");
        }
    }

    private static int[] findSubarrayWithAverage(int[] arr, double target) {
        HashMap<Double, Integer> hm = new HashMap<>();
        hm.put(0.0, -1);
        double runningSum = 0;

        for (int i = 0 ; i < arr.length ; i++) {
            runningSum += (arr[i] - target);
            if (hm.containsKey(runningSum)) {
                return new int[] {hm.get(runningSum)+1, i};
            }
            hm.put(runningSum, i);
        }
        return new int[] {-1,-1};
    }
}
