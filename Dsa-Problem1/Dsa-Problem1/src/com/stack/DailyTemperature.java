package com.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperature {
    public static void main(String[] args) {
        int[] temperature = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] answer = dailyTemperatures(temperature);
        System.out.println(Arrays.toString(answer));
    }

    private static int[] dailyTemperatures(int[] temperature) {
        int n = temperature.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0 ; i < n ; i++) {
            while(!stack.isEmpty() && temperature[i] > temperature[stack.peek()]) {
                int previous = stack.pop();
                ans[previous] = i - previous;
            }
            stack.push(i);
        }
        return ans;
    }
}
