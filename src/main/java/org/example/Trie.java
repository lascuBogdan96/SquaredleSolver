package org.example;

class TrieNode {
    private final TrieNode[] children;
    private boolean isEndOfWord;

    public TrieNode() {
        this.children = new TrieNode[26]; // for 'a' to 'z'
        this.isEndOfWord = false;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }

    public TrieNode getChild(char ch) {
        if (ch - 'a' < 0) {
            System.out.println(ch);
        }
        return children[ch - 'a'];
    }

    public void setChild(char ch, TrieNode node) {
        children[ch - 'a'] = node;
    }

}

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChild(ch);
            if (node == null) {
                node = new TrieNode(); // Pass current node as parent
                current.setChild(ch, node);
            }
            current = node;
        }
        current.setEndOfWord(true);
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChild(ch);
            if (node == null) {
                return false; // word not found
            }
            current = node;
        }
        return current.isEndOfWord(); // true if the word is in the Trie
    }

    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode node = current.getChild(ch);
            if (node == null) {
                return false; // prefix not found
            }
            current = node;
        }
        return true; // prefix found
    }
}