package com.binarySearch;

public class SquareRoot {
	public static void main(String[] args) {
		System.out.println(findSqrt(10));
	}

	//Time complexity = O(log x)
	private static int findSqrt(int x) {
		// TODO Auto-generated method stub
		int left = 1;
		int right = x;
		int ans = 0;
		while (left <= right) {
			int mid = left + (right-left)/2;
			if((long)mid*mid == x) {
				return mid;
			} else if ((long) mid * mid < x) {
				 ans = mid;
				left = mid+1;
			} else {
				right = mid-1;
			}
		}
		return ans;
	}
}
