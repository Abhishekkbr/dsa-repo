package com.slidingWindow;

import java.util.HashSet;

public class DistinctSubstring {
	public static void main(String[] args) {
		String s = "abcdabcbb";
		System.out.println(findLongestDistinct(s));
	}

	private static int findLongestDistinct(String s) {
		// TODO Auto-generated method stub
		HashSet<Character> count = new HashSet<>();
		int left = 0;
		int maxLength = 0;
		for(int right = 0 ; right < s.length() ; right++)  {
			while(count.contains(s.charAt(right))) {
				count.remove(s.charAt(left));
				left++;
			}
			count.add(s.charAt(right));
			maxLength = Math.max(maxLength, right-left + 1);
		}
		
		return maxLength;
	}
}
