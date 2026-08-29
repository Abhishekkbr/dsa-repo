package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupWithAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    private static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> result = new HashMap<>();

        for (String str : strs) {
//            char[] key = str.toCharArray();
//            Arrays.sort(key);
//            result.computeIfAbsent(Arrays.toString(key), k -> new ArrayList<>()).add(str);

            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            String key = Arrays.toString(count);
            result.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(result.values());
    }
}
