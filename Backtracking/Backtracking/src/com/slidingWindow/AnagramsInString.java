package com.slidingWindow;

import java.util.*;
import java.util.Map.Entry;

public class AnagramsInString {
	public static void main(String[] args) {
		String S = "abcba";
		String T = "ab";
		List<Integer> result1 = findAnagrams(S, T);
		List<Integer> result = findAnagramsBest(S, T);
		System.out.println(result1); 
		System.out.println(result); 
	}

	private static List<Integer> findAnagrams(String input, String pattern) {
		List<Integer> result = new ArrayList<>();
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < pattern.length() ; i++) {
			sb.append(input.charAt(i));
		}
		
		if(checkAnagaram(sb.toString(), pattern)) {
			result.add(0);
		}
		
		for(int j = pattern.length() ; j < input.length(); j++) {
			if(sb.length() == pattern.length()) {
				sb.deleteCharAt(0);
				sb.insert(pattern.length()-1, input.charAt(j));
				if(checkAnagaram(sb.toString(), pattern)) {
					result.add(j-pattern.length()+1);
				}
			}
		}
		return result;		
	}
	
	// time complexity O(N+M)
	private static List<Integer> findAnagramsBest(String input, String pattern) {
		List<Integer> result = new ArrayList<>();
		// TODO Auto-generated method stub
		int[] freqIn = new int[26];
		int[] freqOut = new int[26];

		for(char ch : pattern.toCharArray()) {
			freqOut[ch-'a']++;
		}
		for(int i = 0 ; i < pattern.length() ; i++) {
			freqIn[input.charAt(i)-'a']++;
		}
		if(Arrays.equals(freqOut, freqIn)) {
			result.add(0);
		}
		
		for(int j = pattern.length() ; j < input.length(); j++) {
			freqOut[input.charAt(j)-'a']++;
			freqOut[input.charAt(j-pattern.length())-'a']--;
			
			if(Arrays.equals(freqIn, freqOut)) {
				result.add(j-pattern.length()+1);
			}
		}
		return result;		
	}

	private static boolean checkAnagaram(String input, String pattern) {
		// TODO Auto-generated method stub
		char[] in = input.toCharArray();
		char[] out = pattern.toCharArray();
		Arrays.sort(in);
		Arrays.sort(out);
		return Arrays.equals(in, out);
	}
}
