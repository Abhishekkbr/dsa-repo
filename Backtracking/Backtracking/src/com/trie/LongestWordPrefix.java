package com.trie;

import java.util.LinkedList;
import java.util.Queue;

public class LongestWordPrefix {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode curr = root;
            for (char ch :  word.toCharArray()) {
                int idx = ch - 'a';
                if(curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
            }
            curr.word = word;
        }

        public String longestWord() {
            String result = "";
            Queue<TrieNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TrieNode node = queue.poll();
                for (int i = 25 ; i >= 0 ; i--) {  // if two words are there to find lexicographically smaller such as apple and banana so output will be apple
                    TrieNode child = node.children[i];
                    if(child != null && child.word != null) {
                        queue.offer(child);

                        if (child.word.length() > result.length() ||
                                (child.word.length() == result.length() && child.word.compareTo(result) < 0)) {
                            result = child.word;
                        }
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String[] words = {"w","wo","wor","worl", "world" ,"banana"};
        System.out.println("Longest word: " + longestWord(words)); // Output: world
    }

    private static String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        return trie.longestWord();
    }
}
