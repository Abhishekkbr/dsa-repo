package com.binarySearch;

/*
 hours = ceil(pile / k)
 koko take 1 hour to eat 1 pile with minimal speed
 */
public class KokoBanana {
	public static void main(String[] args) {
		int[] piles = {3,6,7,11}; 
		int hours = 8;
		System.out.println(minEatingSpeed(piles, hours));
	}

	//Time complexity = O(n*log m)      m = binary serarch for max (right) n = canEat()
	private static int minEatingSpeed(int[] piles, int hours) {
		// TODO Auto-generated method stub
		int left = 1;
		int right = 0;
		for(int p : piles) {
			right = Math.max(p, right);
		}
		while(left < right) {
			int mid = left + (right-left) / 2;
			if(canEat(piles, hours, mid)) {
				right = mid;     // one possible solution but we need to try smaller speed we don't use mid-1 as we are not storing mid in spearate because left<right
			}
			else {
				left = mid+1;    // speed too small need to increase
			}
		}
		
		return left;     // or right — they are equal now because we are doing left < right not left <= right (in this we can use separate variable)
	}

	private static boolean canEat(int[] piles, int hours, int mid) {
		// TODO Auto-generated method stub
		int currHour = 0;
		for(int p : piles) {
			currHour += (p + mid - 1) / mid;    // same as ceil(pile / k)
		}
		return currHour <= hours;
	}
}
