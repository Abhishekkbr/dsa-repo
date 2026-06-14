package com.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    private void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode root, String word, int index) {
        if (index == word.length()) {
            if (!root.isEndOfWord) return false;
            root.isEndOfWord = false;
            return root.children.isEmpty();
        }

        char c = word.charAt(index);
        TrieNode child = root.children.get(c);
        if (child == null) return false;

        boolean deleteChild = delete(root, word, index+1);

        if (deleteChild) {
            root.children.remove(c);
            return root.children.isEmpty() && !root.isEndOfWord;
        }
        return false;
    }

    private boolean startsWith(String word) {
        return findNode(word) != null;
    }

    private boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    private TrieNode findNode(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) return null;
            curr = curr.children.get(ch);
        }
        return curr;
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            curr.children.putIfAbsent(ch, new TrieNode());
            curr = curr.children.get(ch);
        }
        curr.isEndOfWord = true;
    }

    public static void main(String[] args) {
        com.trie.Trie searchTrie = new com.trie.Trie();

        searchTrie.insert("apple");
        searchTrie.insert("app");
        searchTrie.insert("apply");

        System.out.println(searchTrie.search("app"));      // true
        System.out.println(searchTrie.search("appl"));     // false (not a complete word)
        System.out.println(searchTrie.startsWith("appl")); // true (prefix exists)

        searchTrie.delete("apple");
        System.out.println(searchTrie.search("apple"));    // false
        System.out.println(searchTrie.search("app"));      // true  (still exists)
        System.out.println(searchTrie.startsWith("appl")); // true  (because "apply" still has this prefix)

        searchTrie.delete("apply");
        System.out.println(searchTrie.startsWith("appl")); // false (no word left with this prefix)
        System.out.println(searchTrie.search("app"));      // true  (untouched)
    }
}
