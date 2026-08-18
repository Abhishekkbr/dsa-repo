package com.arrays;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int answer = maxArea(height);
        System.out.println(answer);
    }

    private static int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int maxArea = 0;
        while (left < right) {
            int width = right - left;
            int containerHeight = Math.min(height[left], height[right]);

            maxArea = Math.max(maxArea, width * containerHeight);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
