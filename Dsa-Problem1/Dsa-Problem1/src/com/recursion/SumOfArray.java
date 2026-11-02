package com.recursion;

public class SumOfArray {
    public static void main(String[] args) {
        int[] arr = new int[] {2,2,3,4,5};
        int sum = sumOfArray(arr, 0, 0);
        System.out.println(sum);
    }

    private static int sumOfArray(int[] input, int currSum, int index) {
//        if (index == input.length) {
//            return currSum;
//        }
//        currSum += input[index];
//        return sumOfArray(input, currSum, index+1);
        if (index == input.length) return 0;
        return input[index] + sumOfArray(input, currSum, index+1);
    }
}
