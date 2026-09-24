package com.arrays;

public class FrequencyInSortedArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 3, 5, 7, 7, 7, 7, 9};
        System.out.println(countFrequency(arr, 2));   // 3
        System.out.println(countFrequency(arr, 7));   // 4
        System.out.println(countFrequency(arr, 4));   // 0 (not present)
    }

    private static int countFrequency(int[] arr, int target) {
        int firstIndex = findFirstIndex(arr, target);
        if (firstIndex == -1) return 0;  // target not exist
        int lastIndex = findLastIndex(arr, target);

        return lastIndex - firstIndex + 1;
    }

    private static int findFirstIndex(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    private static int findLastIndex(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
