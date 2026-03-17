package com.slidingWindow;

import java.util.*;

public class MinimumSubstring {
	public static void main(String[] args) {
		String input = "ADBECODEBANC";
		String target = "ABC";
		System.out.println(minSubstring(input, target));
	}

	private static String minSubstring(String input, String target) {
		// TODO Auto-generated method stub
		Map<Character, Integer> mp = new HashMap<>();
		for(Character c : target.toCharArray()) {
			mp.put(c, mp.getOrDefault(c, 0)+1);
		}
		int right = 0; int left = 0; int minLength = Integer.MAX_VALUE;
		int minStart = 0;
		int formed = 0;
		while(right < input.length()) {
			char rightChar = input.charAt(right);
			if(mp.containsKey(rightChar)) {
				mp.put(rightChar, mp.get(rightChar)-1);
				if(mp.get(rightChar) == 0) {
					formed++;
				}
			}
			
			while(left <= right && formed == mp.size()) {
				if(right-left+1 < minLength) {
					minLength = right-left+1;
					minStart = left;
				}
				char leftChar = input.charAt(left);
				if(mp.containsKey(leftChar)) {
					mp.put(leftChar, mp.get(leftChar)+1);
					if(mp.get(leftChar) > 0) {
						formed--;
					}
				}
				left++;
			}
			right++;
		}
		return minLength == Integer.MAX_VALUE ? "" : input.substring(minStart, minStart+minLength);
	}
	
	private static String minSubstringTest(String input, String target) {
		// TODO Auto-generated method stub
		Map<Character, Integer> mp = new HashMap<>();
		for(Character c : target.toCharArray()) {
			mp.put(c, mp.getOrDefault(mp.get(c), 0)+1);
		}
		int right = 0; int left = 0; int minLength = Integer.MAX_VALUE;
		int formed = 0;
		int minStart = 0;
		while(right < input.length()) {
			if(mp.containsKey(input.charAt(right))) {
				mp.put(input.charAt(right), mp.get(input.charAt(right))-1);
				if(mp.get(input.charAt(right)) == 0) {
					formed++;
				}
			}
			while(left<=right && formed == mp.size()) {
				if(right-left+1 < minLength) {
					minLength = right-left+1;
					minStart = left;
				}
				char leftChar = input.charAt(left);
				if(mp.containsKey(leftChar)) {
					mp.put(leftChar, mp.get(leftChar)+1);
					if(mp.get(leftChar) > 0) {
						formed--;
					}
				}
				
				left++;
			}
			
			right++;
		}
		return minLength == Integer.MAX_VALUE ? "" : input.substring(minStart, minStart+minLength);
		
	}
}
