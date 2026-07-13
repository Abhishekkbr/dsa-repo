package com.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// TIme Complexity O(n^t)  n = input size, t = target
public class CombinationSum {
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(2,3,8,6);
		int target = 8;
		List<List<Integer>> result = combinationSum(input, target);
		System.out.println(result);
	}

	private static List<List<Integer>> combinationSum(List<Integer> input, int target) {
		// TODO Auto-generated method stub
		List<Integer> currentResult = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		Collections.sort(input);
		combinationSumResult(input, target, currentResult, result, 0);
		return result;
	}

	private static void combinationSumResult(List<Integer> input, int target, List<Integer> currentResult,
			List<List<Integer>> result, int index) {
		// TODO Auto-generated method stub
		if(target == 0) {
			result.add(new ArrayList<>(currentResult));
			return;
		}

		for(int i = index ; i < input.size(); i++) {
			if(input.get(i) > target) {
				break;
			}
			currentResult.add(input.get(i));
			combinationSumResult(input, target - input.get(i), currentResult, result, i);
			currentResult.removeLast();
		}
		
	}
}
