package com.arrays;

import java.util.Arrays;

public class WiggleSort {
    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        wiggleSort(nums);
        Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
    }

    private static void wiggleSort(int[] nums) {
        int[] sorted = nums.clone();
        int n = nums.length;
        Arrays.sort(sorted);
        int mid = (n+1) / 2;
        int smallIdx = mid - 1;
        int largeIdx = n - 1;

        for (int i = 0 ; i < n ; i++) {
            if (i % 2 == 0) {
                nums[i] = sorted[smallIdx--];
            } else {
                nums[i] = sorted[largeIdx--];
            }
        }
    }
}
