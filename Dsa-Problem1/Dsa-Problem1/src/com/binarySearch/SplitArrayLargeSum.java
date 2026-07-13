package com.binarySearch;

public class SplitArrayLargeSum {
	public static void main(String[] args) {
		int[] nums = {7, 2, 5, 10, 8};
		int k = 2;
		System.out.println(splitArray(nums, k));
	}

	// Time complexity - O(n * log(sum(nums)))
	private static int splitArray(int[] nums, int k) {
		// TODO Auto-generated method stub
		int left = 0;
		int right = 0;
		
		for(int num: nums) {
			left = Math.max(left, num);
			right += num;
		}
		
		while(left < right) {
			int mid = left + (right-left)/2;
			if(canSplit(nums, mid, k)) {
				right = mid;	// try a smaller one
			} else {
				left = mid + 1;  // mid is too small
			}
		}
		
		return left;
	}

	private static boolean canSplit(int[] nums, int mid, int k) {
		// TODO Auto-generated method stub
		int count = 1;
		int currentSum = 0;
		for(int num : nums) {
			if (currentSum+num > mid) {
				count++;			// we need a new subarray
				currentSum = num;   // start new subarray with current number
				if (count > k) return false;   // too many subarrays
			} else {
				currentSum += num;
			}
		}
		return true;
	}
}
