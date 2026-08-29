package com.stack;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangle {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int answer = largestRectangle(heights);
        System.out.println(answer);
    }

    private static int largestRectangle(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0 ; i <= heights.length ; i++) {
            int currentHeight = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                int area = height * width;
                maxArea = Math.max(area, maxArea);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
