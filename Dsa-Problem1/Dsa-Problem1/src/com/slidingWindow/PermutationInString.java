package com.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {
	public static void main(String[] args) {
		String s1 = "eb";
        String s2 = "eidbaoaoo";
        boolean result = checkInclusion(s1, s2);
        System.out.println(result);
	}

	private static boolean checkInclusion(String s1, String s2) {
		// TODO Auto-generated method stub
		Map<Character, Integer> s1Map = new HashMap<>();
		for(Character c : s1.toCharArray()) {
			s1Map.put(c, s1Map.getOrDefault(c, 0)+1);
		}
		Map<Character, Integer> s2Map = new HashMap<>();
		for(int i = 0 ; i < s1.length() ; i++) {
			s2Map.put(s2.charAt(i), s2Map.getOrDefault(s2.charAt(i), 0)+1);
		}
		
		if(s2Map.equals(s1Map)) {
			return true;
		}
		
		for(int j = s1.length() ; j < s2.length() ; j++) {
			Character newChar = s2.charAt(j);
			s2Map.put(newChar, s2Map.getOrDefault(newChar, 0)+1);
			
			Character oldChar = s2.charAt(j-s1.length());
			s2Map.put(oldChar, s2Map.getOrDefault(oldChar, 0)-1);
			
			if(s2Map.get(oldChar)==0) {
				s2Map.remove(oldChar);
			}
			
			if(s2Map.equals(s1Map)) {
				return true;
			}
		}
		
		
		return false;
	}
}
