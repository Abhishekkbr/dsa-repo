package com.backtrack;

public class ClimbStairs {
	public static void main(String[] args) {
		int totalSteps = 4;
		System.out.println(countSteps(totalSteps, 0, ""));
	}

	private static int countSteps(int totalSteps, int currentSteps, String path) {
		// TODO Auto-generated method stub
		if(currentSteps == totalSteps) {
			System.out.println(path);
			return 1;
		}
		
		if(currentSteps > totalSteps) {
			return 0;
		}
		int ways = 0;
		
		ways+=countSteps(totalSteps, currentSteps+1, path+"1 ");
		ways+=countSteps(totalSteps, currentSteps+2, path+"2 ");
		
		return ways;
	}
}
