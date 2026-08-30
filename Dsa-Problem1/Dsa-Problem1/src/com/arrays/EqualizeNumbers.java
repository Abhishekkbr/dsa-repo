package com.arrays;

public class EqualizeNumbers {
    public static void main(String[] args) {
        System.out.println(minOperations(3, 17)); // sample run
    }

    private static int minOperations(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int minOps = Integer.MAX_VALUE;
        int division = 0;
        long current = b;

        while (true) {
            long diff = Math.abs(a - current);
            long cost = division + diff;
            minOps = (int) Math.min(minOps, cost);
            if (current == 0) break;
            current = current/2;
            division++;
        }

        return minOps;
    }
}
