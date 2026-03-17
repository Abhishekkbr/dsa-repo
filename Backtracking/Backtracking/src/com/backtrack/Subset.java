package com.backtrack;

import java.util.*;

public class Subset {
	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>(Arrays.asList(1,2,3));
		List<List<Integer>> result = subset(input);
		System.out.println(result);
		List<Integer> input2 = new ArrayList<>(Arrays.asList(1,2,3,2,3));
		List<List<Integer>> result2 = subsetDuplicate(input2);
		System.out.println(result2);
	}

	private static List<List<Integer>> subset(List<Integer> input) {
		// TODO Auto-generated method stubL
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> current = new ArrayList<>();
		
		return findSubset(result, current, input, 0);
	}

	private static List<List<Integer>> findSubset(List<List<Integer>> result, List<Integer> current,
			List<Integer> input, int index) {
		// TODO Auto-generated method stub
		if(!current.isEmpty()) {
			result.add(new ArrayList<>(current));
		}
		for(int i = index ; i < input.size() ; i++) {
			current.add(input.get(i));
			findSubset(result, current, input, i+1);
			current.removeLast();
		}
		return result;
	}
	
	private static List<List<Integer>> subsetDuplicate(List<Integer> input) {
		// TODO Auto-generated method stubL
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> current = new ArrayList<>();
		Collections.sort(input);
		
		return findSubsetDuplicate(result, current, input, 0);
	}

	private static List<List<Integer>> findSubsetDuplicate(List<List<Integer>> result, List<Integer> current,
			List<Integer> input, int index) {
		// TODO Auto-generated method stub
		result.add(new ArrayList<>(current));
		for(int i = index; i<input.size(); i++) {
			if(i > index && input.get(i) == input.get(i-1)) {
				continue;
			}
			current.add(input.get(i));
			findSubset(result, current, input, i+1);
			current.remove(current.size()-1);
		}
		return result;
	}
}
