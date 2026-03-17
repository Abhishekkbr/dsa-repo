package com.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringTwoDistinct {
	public static void main(String[] args) {
		String s = "ccaacabb";
        int result = lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(result); 
	}

	private static int lengthOfLongestSubstringTwoDistinct(String s) {
		// TODO Auto-generated method stub
		Map<Character, Integer> map = new HashMap<>();
		int right = 0; int left = 0; int maxLength = 0;
		while(right < s.length()) {
			map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)+1);
			right++;
			
			while(map.size() > 2) {
				if(map.containsKey(s.charAt(left))) {
					map.put(s.charAt(left), map.get(s.charAt(left))-1);
					if (map.get(s.charAt(left)) == 0) {
						map.remove(s.charAt(left));
					}
					left++;
				}
			}
			maxLength = Math.max(maxLength, right-left);
		}
		
		return maxLength;
	}
}
