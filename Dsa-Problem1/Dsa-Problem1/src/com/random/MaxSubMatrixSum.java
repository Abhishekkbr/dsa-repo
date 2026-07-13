package com.random;

public class MaxSubMatrixSum {
    public static void main(String[] args) {
        MaxSubMatrixSum sol = new MaxSubMatrixSum();
        int[][] matrix = {
                { 1, -2,  3},
                {-4,  5,  6},
                { 7, -8,  9}
        };
        System.out.println(sol.maxSubMatrixSum(matrix)); // Output: 18
    }

    private int maxSubMatrixSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        for (int left = 0 ; left < cols ; left++) {
            int[] rowSum = new int[rows];
            for (int right = left ; right < cols ; right++) {
                for (int row = 0 ; row < rows ; row++) {
                    rowSum[row] += matrix[row][right];
                }
                int currMax = kandane(rowSum);
                maxSum = Math.max(currMax, maxSum);
            }
        }
        return maxSum;
    }

    private int kandane(int[] rowSum) {
        int curr = rowSum[0];
        int maxSum = rowSum[0];

        for (int i = 1 ; i < rowSum.length ; i++) {
            curr = Math.max(rowSum[i], curr + rowSum[i]);
            maxSum = Math.max(curr, maxSum);
        }
        return maxSum;
    }
}
