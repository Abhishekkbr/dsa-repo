package com.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 6;

        generateSubset(arr, target);
    }

    private static void generateSubset(int[] input, int target) {
        List<Integer> curr = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(input);
        generateCombinationSubset(input, target, curr, result, 0);
        System.out.println(result);
    }

    private static void generateCombinationSubset(int[] input, int target, List<Integer> curr, List<List<Integer>> result, int index) {
        if (target == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for(int i = index ; i < input.length ; i++) {
            if (input[i] > target) {
                break;
            }
            curr.add(input[i]);
            generateCombinationSubset(input, target - input[i], curr, result, i+1);
            curr.removeLast();
        }
    }
}
