package com.binarySearch;

public class ShipProblem {
	public static void main(String[] args) {
		int[] weights = {3,2,2,4,1,4};
		int days = 3;
		System.out.println(minCapacity(weights, days));
	}

	//time complexity = O(n × log(sum))
	private static int minCapacity(int[] weights, int days) {
		// TODO Auto-generated method stub
		int left = 0;
		int right = 0;
		
		for(int weight : weights) {
			left = Math.max(left, weight);
			right += weight;
		}
		
		while(left < right) {
			int mid = left + (right-left)/2;
			
			if(canShip(weights, days, mid)) {
				right = mid;
			} else {
				left = mid+1;
			}
			
		}
		return left;
	}

	private static boolean canShip(int[] weights, int days, int mid) {
		// TODO Auto-generated method stub
		int currentLoad = 0;
		int requiredDays = 1;
		for(int weight : weights) {
			if(currentLoad+weight > mid) {
				requiredDays++;
				currentLoad = 0;
			}
			currentLoad += weight;
		}
		return requiredDays <= days;
	}
}
