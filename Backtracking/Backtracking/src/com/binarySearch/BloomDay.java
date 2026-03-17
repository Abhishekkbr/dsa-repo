package com.binarySearch;

public class BloomDay {
	public static void main(String[] args) {
		int[] bloomDay = {7,7,7,7,12,7,7};
		int m = 2;
		int k = 3;
		System.out.println(minDays(bloomDay, m, k));
	}

	// TIme = O(n log(max_day - min_day))   space = O(1)
	private static int minDays(int[] bloomDay, int m, int k) {
		// TODO Auto-generated method stub
		int totalFlowerNeeded = m * k;
		if((long)totalFlowerNeeded > bloomDay.length) return -1;
		int left = Integer.MAX_VALUE;
		int right = Integer.MIN_VALUE;
		for(int bloom : bloomDay) {
			left = Math.min(left, bloom);
			right = Math.max(right, bloom);
		}
		while(left < right) {
			int mid = left + (right - left) / 2;
			if(canMakeBouquets(bloomDay, m, k, mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	private static boolean canMakeBouquets(int[] bloomDay, int m, int k, int mid) {
		// TODO Auto-generated method stub
		int count = 0;
		int flowers = 0;
		for(int bloom : bloomDay) {
			if(bloom <= mid) {
				flowers++;
				if(flowers == k) {
					count++;
					flowers = 0;
				}
			} else {
				flowers = 0;
			}
		}
		return count >= m;
	}
}
